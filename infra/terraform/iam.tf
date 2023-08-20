data "aws_iam_policy_document" "assume_role" {
  statement {
    effect = "Allow"

    principals {
      type        = "Service"
      identifiers = ["ec2.amazonaws.com"]
    }

    actions = ["sts:AssumeRole"]
  }
}

resource "aws_iam_role" "k8s_instance_role" {
  name               = "k8s-instance-role"
  assume_role_policy = data.aws_iam_policy_document.assume_role.json
}

data "aws_iam_policy_document" "ssm_parameter_allow_all_policy" {
  statement {
    effect = "Allow"
    actions = [
      "ssm:PutParameter",
      "ssm:DeleteParameter",
      "ssm:GetParameterHistory",
      "ssm:GetParametersByPath",
      "ssm:GetParameters",
      "ssm:GetParameter",
      "ssm:DeleteParameters"
    ]
    resources = ["*"]
  }
}

resource "aws_iam_policy" "ssm_parameter_allow_all_policy" {
  name        = "ssm_parameter_allow_all_policy"
  description = "allow all ssm parameter actions"
  policy      = data.aws_iam_policy_document.ssm_parameter_allow_all_policy.json
}

resource "aws_iam_instance_profile" "k8s_instance_profile" {
  name = "k8s-instance-profile"

  role = aws_iam_role.k8s_instance_role.name
}

resource "aws_iam_role_policy_attachment" "ssm-parameter-allow-all-policy-attachment" {
  role       = aws_iam_role.k8s_instance_role.name
  policy_arn = aws_iam_policy.ssm_parameter_allow_all_policy.arn
}

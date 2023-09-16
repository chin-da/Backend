data "aws_ami" "amazon_linux_2_x86_64" {
  most_recent = true

  filter {
    name   = "owner-alias"
    values = ["amazon"]
  }

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm*"]
  }
}

data "aws_ami" "amazon_linux_2_arm_64" {
  most_recent = true

  filter {
    name   = "owner-alias"
    values = ["amazon"]
  }

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm*"]
  }

  filter {
    name   = "architecture"
    values = ["arm64"]
  }
}


data "aws_key_pair" "ec2-ssh-key" {
  key_name = "${var.project_name}-ec2-ssh-key"
}

resource "aws_instance" "master_node_1" {
  ami           = data.aws_ami.amazon_linux_2_arm_64.id
  instance_type = "t4g.small"
  key_name      = data.aws_key_pair.ec2-ssh-key.key_name

  subnet_id              = module.vpc.public_subnets[0]
  vpc_security_group_ids = [aws_security_group.ec2_ssh_allow_group.id, aws_security_group.vpc_traffic_allow_group.id, aws_security_group.all_out_bound_allow_group.id, aws_security_group.ec2_internet_inbound_allow_group.id]

  associate_public_ip_address = true

  user_data = file("../script/init_node.sh")

  tags = {
    Name = "master-node-1"
  }

  iam_instance_profile = aws_iam_instance_profile.k8s_instance_profile.name

  lifecycle {
    ignore_changes = [
      ami, user_data
    ]
  }
}

resource "aws_instance" "worker_node_1" {
  ami           = data.aws_ami.amazon_linux_2_x86_64.id
  instance_type = "t2.micro"
  key_name      = data.aws_key_pair.ec2-ssh-key.key_name

  subnet_id              = module.vpc.public_subnets[0]
  vpc_security_group_ids = [aws_security_group.ec2_ssh_allow_group.id, aws_security_group.vpc_traffic_allow_group.id, aws_security_group.all_out_bound_allow_group.id, aws_security_group.ec2_internet_inbound_allow_group.id]

  # Temporary deploy instance in public subnet and give public IP for test
  associate_public_ip_address = true

  user_data = file("../script/init_node.sh")

  iam_instance_profile = aws_iam_instance_profile.k8s_instance_profile.name

  tags = {
    Name = "worker-node-1"
  }

  lifecycle {
    ignore_changes = [
      ami, user_data
    ]
  }
}

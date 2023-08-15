resource "aws_security_group" "ec2_ssh_allow_group" {
  name        = "ec2_ssh_allow_group"
  description = "Allow ssh inboud traffic"
  vpc_id      = module.vpc.vpc_id

  ingress {
    description = "HTTPS ingress"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["175.208.213.141/32"]
  }
}

resource "aws_security_group" "vpc_traffic_allow_group" {
  name        = "vpc_traffic_allow_group"
  description = "Allow traffic inside the vpc"
  vpc_id      = module.vpc.vpc_id

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = -1
    cidr_blocks = ["10.0.0.0/16"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["10.0.0.0/16"]
  }
}

resource "aws_security_group" "all_out_bound_allow_group" {
  name        = "all_out_bound_allow_group"
  description = "Allow traffic from the vpc, allow all traffic to the internet"
  vpc_id      = module.vpc.vpc_id

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

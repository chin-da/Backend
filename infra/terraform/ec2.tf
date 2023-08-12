data "aws_ami" "amazon_linux_2" {
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

data "aws_key_pair" "ec2-ssh-key" {
  key_name = "${var.project_name}-ec2-ssh-key"
}

resource "aws_instance" "master_node" {
  ami           = data.aws_ami.amazon_linux_2.id
  instance_type = "t2.micro"
  key_name      = data.aws_key_pair.ec2-ssh-key.key_name

  subnet_id              = aws_subnet.public_subnet_1.id
  vpc_security_group_ids = [aws_security_group.ec2_ssh_allow_group.id]

  # Temporary deploy instance in public subnet and give public IP for test
  associate_public_ip_address = true

  tags = {
    Name = "master-node"
  }

}
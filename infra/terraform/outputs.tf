output "ec2_public_ip" {
  value = aws_instance.master_node.public_ip
}
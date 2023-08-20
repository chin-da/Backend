output "master_node_1_public_ip" {
  value = aws_instance.master_node_1.public_ip
}

output "worker_node_1_public_ip" {
  value = aws_instance.worker_node_1.public_ip
}
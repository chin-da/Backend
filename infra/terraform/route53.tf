resource "aws_route53_zone" "primary" {
  name = "chinda.kr"
}

resource "aws_route53_record" "api" {
  zone_id = aws_route53_zone.primary.zone_id
  name    = "api.chinda.kr"
  type    = "A"
  ttl     = "30"
  records = [aws_instance.master_node_1.public_ip, aws_instance.worker_node_1.public_ip]
}
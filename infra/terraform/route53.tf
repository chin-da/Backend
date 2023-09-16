variable "domain_name" {
  type    = string
  default = "chinda.kr"
}

variable "vercel_ip_address" {
  type    = string
  default = "76.76.21.21"
}

resource "aws_route53_zone" "primary" {
  name = var.domain_name
}

resource "aws_route53_record" "vercel" {
  zone_id = aws_route53_zone.primary.zone_id
  name    = var.domain_name
  type    = "A"
  ttl     = "30"
  records = ["76.76.21.21"]
}

resource "aws_route53_record" "www_to_root" {
  zone_id = aws_route53_zone.primary.zone_id
  name    = "www.chinda.kr"
  type    = "CNAME"
  ttl     = "30"
  records = [var.domain_name]
}

resource "aws_route53_record" "api" {
  zone_id = aws_route53_zone.primary.zone_id
  name    = "api.${var.domain_name}"
  type    = "A"
  ttl     = "30"
  records = [aws_instance.master_node_1.public_ip, aws_instance.worker_node_1.public_ip]
}
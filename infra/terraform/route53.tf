resource "aws_route53_zone" "primary" {
  name = "chinda.kr"
}

resource "aws_route53_zone" "api" {
  name = "api.chinda.kr"
}

resource "aws_route53_record" "api-ns" {
  zone_id = aws_route53_zone.primary.zone_id
  name    = "api.chinda.kr"
  type    = "NS"
  ttl     = "30"
  records = aws_route53_zone.api.name_servers
}
resource "aws_ssm_parameter" "db_url" {
  name      = "/chinda/db/url"
  type      = "String"
  value     = aws_db_instance.chinda_mysql.address
  overwrite = true
}

resource "aws_ssm_parameter" "db_username" {
  name      = "/chinda/db/username"
  type      = "SecureString"
  value     = var.db_username
  overwrite = true
}

resource "aws_ssm_parameter" "db_password" {
  name      = "/chinda/db/password"
  type      = "SecureString"
  value     = var.db_password
  overwrite = true
}

resource "aws_ssm_parameter" "chinda_public_key" {
  name      = "/chinda/token/public_key_pem"
  type      = "String"
  value     = file("./keys/chinda_public.pem")
  overwrite = true
}

resource "aws_ssm_parameter" "chinda_private_key" {
  name      = "/chinda/token/private_key_pem"
  type      = "SecureString"
  value     = file("./keys/chinda_private.pem")
  overwrite = true
}

resource "aws_ssm_parameter" "chinda_kakao_client_id" {
  name      = "/chinda/oauth/kakao_client_id"
  type      = "String"
  value     = var.chinda_kakao_client_id
  overwrite = true
}

resource "aws_ssm_parameter" "chinda_redirect_uri" {
  name      = "/chinda/oauth/redirect_uri"
  type      = "String"
  value     = var.chinda_redirect_uri
  overwrite = true
}

variable "chinda_kakao_client_id" {
  description = "kakao client id"
  type        = string
  sensitive   = false
}

variable "chinda_redirect_uri" {
  description = "oauth redirect uri"
  type        = string
  sensitive   = false
}

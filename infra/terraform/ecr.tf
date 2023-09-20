resource "aws_ecr_repository" "chinda_iam" {
  name                 = "chinda_iam"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}
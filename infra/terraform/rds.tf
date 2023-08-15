resource "aws_db_instance" "chinda_mysql" {
  engine         = "mysql"
  engine_version = "8.0.33"
  instance_class = "db.t3.micro"

  db_subnet_group_name   = aws_db_subnet_group.single_az.name
  vpc_security_group_ids = [aws_security_group.vpc_traffic_allow_group.id]

  storage_type      = "gp2"
  allocated_storage = 20

  identifier = "chinda-mysql"

  skip_final_snapshot = true
  storage_encrypted   = false
  publicly_accessible = false
  multi_az            = false

  username          = var.db_username
  password          = var.db_password
  apply_immediately = true
}

resource "aws_db_subnet_group" "single_az" {
  name       = "single-az"
  subnet_ids = [module.vpc.private_subnets[0], module.vpc.private_subnets[1]]
}

variable "db_username" {
  description = "RDS root user name"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "RDS root user password"
  type        = string
  sensitive   = true
}

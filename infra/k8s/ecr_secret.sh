kubectl create secret docker-registry regcred \
    --docker-server=627145993949.dkr.ecr.ap-northeast-2.amazonaws.com \
    --docker-username=AWS \
    --docker-password=$(aws ecr get-login-password --region ap-northeast-2)

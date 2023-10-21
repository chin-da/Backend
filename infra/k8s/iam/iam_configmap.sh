kubectl create configmap iam-config \
  --from-literal=kakao-client-id=$(aws ssm get-parameter --name "/chinda/oauth/kakao_client_id" --query Parameter.Value --output text --region ap-northeast-2) \
  --from-literal=redirect-uri=$(aws ssm get-parameter --name "/chinda/oauth/redirect_uri" --query Parameter.Value --output text --region ap-northeast-2)
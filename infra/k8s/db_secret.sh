kubectl create secret generic db-secret \
  --from-literal=url=$(aws ssm get-parameter --name "/chinda/db/url" --query Parameter.Value --output text --region ap-northeast-2)/chinda \
  --from-literal=username=$(aws ssm get-parameter --name "/chinda/db/username" --with-decryption --query Parameter.Value --output text --region ap-northeast-2) \
  --from-literal=password=$(aws ssm get-parameter --name "/chinda/db/password" --with-decryption --query Parameter.Value --output text --region ap-northeast-2) 
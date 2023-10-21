kubectl create secret generic token-secret \
  --from-literal=public-key-pem="$(aws ssm get-parameter --name "/chinda/token/public_key_pem" --query Parameter.Value --output text --region ap-northeast-2)" \
  --from-literal=private-key-pem="$(aws ssm get-parameter --name "/chinda/token/private_key_pem" --with-decryption --query Parameter.Value --output text --region ap-northeast-2)" 
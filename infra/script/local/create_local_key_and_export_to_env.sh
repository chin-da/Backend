openssl genrsa -out chinda_private.pem 2048
openssl rsa -in chinda_private.pem -pubout -out chinda_public.pem

echo export jwt-public-key-pem=$(cat chinda_public.pem) >> ~/.zshenv
echo export jwt-private-key-pem=$(cat chinda_private.pem) >> ~/.zshenv
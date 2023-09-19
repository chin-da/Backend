openssl genrsa -out chinda_private.pem 2048
openssl rsa -in chinda_private.pem -pubout -out chinda_public.pem

echo "export JWT_PUBLIC_KEY_PEM='$(cat chinda_public.pem)'" >> ~/.zshrc
echo "export JWT_PRIVATE_KEY_PEM='$(cat chinda_private.pem)'" >> ~/.zshrc
#!/bin/sh
set -e

APP_DIR=$(dirname $0)
cd "$APP_DIR"

IMAGE_DIR="$1"
IMAGE_NAME="chinda_$IMAGE_DIR"
IMAGE_TAG=$(git rev-parse --short HEAD)
ECR_REPO="627145993949.dkr.ecr.ap-northeast-2.amazonaws.com"

docker buildx build . \
    --platform linux/amd64,linux/arm64 \
    -t $ECR_REPO/$IMAGE_NAME:$IMAGE_TAG \
    -f ./$IMAGE_DIR/Dockerfile \
    --push

echo "Successfully built image: $IMAGE_NAME:$IMAGE_TAG"


if [ "$2" = "push" ]; then
    aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin $ECR_REPO
    docker tag "$IMAGE_NAME:$IMAGE_TAG" "$ECR_REPO/$IMAGE_NAME:$IMAGE_TAG"
    docker push "$ECR_REPO/$IMAGE_NAME:$IMAGE_TAG"
    docker tag "$IMAGE_NAME:$IMAGE_TAG" "$ECR_REPO/$IMAGE_NAME:latest"
    docker push "$ECR_REPO/$IMAGE_NAME:latest"
    echo "Successfully pushed to ECR: $IMAGE_NAME:$IMAGE_TAG"
fi

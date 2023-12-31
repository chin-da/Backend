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
    -t $ECR_REPO/$IMAGE_NAME:latest \
    -f ./$IMAGE_DIR/Dockerfile \
    --push

echo "Successfully push image: $IMAGE_NAME:$IMAGE_TAG"

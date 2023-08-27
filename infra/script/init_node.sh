#!/bin/bash

yum update -y
yum install git -y

mkdir ~/chinda
cd ~/chinda
git init
git remote add -f origin https://github.com/chin-da/Backend.git
git config core.sparseCheckout true
echo "infra" >>.git/info/sparse-checkout
# TODO: change branch name to main before merge
git pull origin fix/#18-change-network-plugin

cd ~/chinda/infra/script
chmod +x *.sh
sh ./set_container_runtime_and_kubeadm.sh

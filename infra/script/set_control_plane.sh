public_ip=$(ip add show eth0 | grep -Po 'inet \K[\d.]+')
cluster_ip_range=$(echo 10.244.0.0/16)
kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=$public_ip --ignore-preflight-errors 'NumCPU,Mem'

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

curl -L https://github.com/weaveworks/weave/releases/download/v2.8.1/weave-daemonset-k8s.yaml -o weave-daemonset-k8s.yaml
sed -i "/^          containers:/a\        - name: weave\n          env:\n            - name: IPALLOC_RANGE\n              value: $cluster_ip_range" weave-daemonset-k8s.yaml
kubectl apply -f weave-daemonset-k8s.yaml

aws ssm put-parameter --name "chinda/k8s/control_plane_endpoint" --value "$public_ip:6443" --type "String"
aws ssm put-parameter --name "chinda/k8s/join_token" --value "$(kubeadm token list | awk 'NR==2{print $1}')" --type "SecureString"
aws ssm put-parameter --name "chinda/k8s/ca_cert_hash" --value "$(openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex)" --type "SecureString"

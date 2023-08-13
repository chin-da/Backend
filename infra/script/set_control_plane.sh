public_ip=$(ip add show eth0 | grep -Po 'inet \K[\d.]+')
cluster_ip_range=$(echo 10.244.0.0/16)
kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=$public_ip --ignore-preflight-errors 'NumCPU,Mem'

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

curl -L https://github.com/weaveworks/weave/releases/download/v2.8.1/weave-daemonset-k8s.yaml -o weave-daemonset-k8s.yaml
vi weave-daemonset-k8s.yaml
kubectl apply -f weave-daemonset-k8s.yaml

public_ip=$(ip add show eth0 | grep -Po 'inet \K[\d.]+')
kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=$public_ip --ignore-preflight-errors 'NumCPU,Mem'

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

curl -L https://github.com/weaveworks/weave/releases/download/v2.8.1/weave-daemonset-k8s.yaml -o weave-daemonset-k8s.yaml
vi weave-daemonset-k8s.yaml
kubectl apply -f weave-daemonset-k8s.yaml

master_node=$(kubectl get nodes | grep control-plane | awk '{print $1}')
kubectl taint node $master_node node-role.kubernetes.io/control-plane:NoSchedule-

echo 'alias k=kubectl' >> ~/.bash_aliases
source ~/.bash_aliases
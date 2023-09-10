public_ip=$(ip add show eth0 | grep -Po 'inet \K[\d.]+')
kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=$public_ip --ignore-preflight-errors 'NumCPU,Mem'

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/tigera-operator.yaml
curl -OL https://raw.githubusercontent.com/projectcalico/calico/v3.26.1/manifests/custom-resources.yaml
sed -i 's/cidr: 192.168.0.0\/16/cidr: 10.244.0.0\/16/g' custom-resources.yaml
kubectl create -f custom-resources.yaml

master_node=$(kubectl get nodes | grep control-plane | awk '{print $1}')
kubectl taint node $master_node node-role.kubernetes.io/control-plane:NoSchedule-

echo 'alias k=kubectl' >> ~/.bash_aliases
source ~/.bash_aliases
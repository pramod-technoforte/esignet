#!/bin/sh
# Uninstalls all esignet helm charts
## Usage: ./delete.sh [kubeconfig]

if [ $# -ge 1 ] ; then
  export KUBECONFIG=$1
fi
NS=idbb-esignet
while true; do
    read -p "Are you sure you want to delete all esignet helm charts?(Y/n) " yn
    if [ $yn = "Y" ]
      then
        helm -n $NS delete esignet
        break
      else
        break
    fi
done

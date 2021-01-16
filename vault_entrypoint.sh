#!/usr/bin/dumb-init /bin/sh
docker-entrypoint.sh server -dev &
while ! vault status | grep Initialized | grep true >/dev/null; do
  echo 'waiting for vault'
  sleep 2s
done

vault login $VAULT_DEV_ROOT_TOKEN_ID
vault secrets disable secret
vault secrets enable -version=1 -path=secret kv

tail -f /dev/null

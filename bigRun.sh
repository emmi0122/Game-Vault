#!/bin/bash
BASE_DIR="."

DIRS=("game-service" "review-service" "user-service")

# Starta terminaler för varje service
for dir in "${DIRS[@]}"; do
  gnome-terminal --title="$dir" -- bash -c "cd $BASE_DIR/$dir && ./run.sh; exec bash"
done

# Starta npm run dev i den mapp du står i
gnome-terminal --title="current-dir-frontend" -- bash -c "cd $BASE_DIR && npm run dev; exec bash"
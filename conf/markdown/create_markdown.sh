#!/bin/bash

filename=$(date +%s)
#content=$(cat template.md)

#printf $content > "$filename.md"

cp "template.md" "$filename.md"

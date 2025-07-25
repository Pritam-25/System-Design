#!/bin/bash

# List of design pattern names
patterns=(
  "Composite Design Pattern"
  "Template Method Pattern"
  "Proxy Design Pattern"
  "Chain of Responsibility Pattern"
  "Bridge Pattern"
  "Builder Design Pattern"
  "Iterator Design Pattern"
  "Flyweight Design Pattern"
  "State Design Pattern"
  "Mediator Design Pattern"
  "Prototype Design Pattern"
  "Visitor Design Pattern"
  "Memento Design Pattern"
)

# Convert pattern name to CamelCase (e.g., "Proxy Design Pattern" -> "ProxyDesignPattern")
to_camel_case() {
  echo "$1" | sed -E 's/(^| )([a-z])/\U\2/g' | tr -d ' '
}

# Loop and create folders and .java files
for pattern in "${patterns[@]}"; do
  folder="$pattern"
  class_name=$(to_camel_case "$pattern")
  mkdir -p "$folder"
  cat > "$folder/${class_name}.java" <<EOF
public class ${class_name} {
    // TODO: Implement the ${class_name} pattern
}
EOF
done

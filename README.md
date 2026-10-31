# MAVIS Commerce Microservices Seed (`mavis-commerce-seed`)

Official end-to-end benchmark & seed repository for testing **MAVIS Platform** across:
1. **Architecture Graph Explorer & 3E Evidence Inspector**
2. **All 8 Canonical Diagrams** (C4 Context, C4 Container, C4 Component, UML Sequence, UML Class, UML Package, UML Activity, UML State Machine, UML Deployment)
3. **Change Impact Analysis & Model Version Diff** (`main` vs `feature/loyalty-tier-v2`)
4. **Runtime Verification Sandbox (Step 1 → Step 4)** with real Render PostgreSQL database and OpenTelemetry distributed tracing.

## Services
| Service | Port | Stack | Responsibilities |
|---|---|---|---|
| `api-gateway` | `8080` | Java 21 / Spring Boot | Entry point REST API + OpenFeign orchestration |
| `order-service` | `8080` | Java 21 / Spring Boot + JPA + Kafka | Order lifecycle state machine, PostgreSQL persistence, Kafka publisher |
| `catalog-service` | `8080` | Java 21 / Spring Boot + JPA + Kafka | Product & category catalog, inventory state machine, Kafka subscriber |
| `payment-service` | `8080` | Java 21 / Spring Boot + JPA + AI Fraud | Payment capture state machine, PostgreSQL persistence, AI risk check |

## Runtime Step 1 Credentials (Render Cloud PostgreSQL)
- **`DB_CONNECTION_STRING`**:
  ```
  jdbc:postgresql://postgres-db:5432/testseed?user=testseed_user&password=<YOUR_RENDER_DB_PASSWORD>&sslmode=require
  ```
- **`DB_PASSWORD`**: `<YOUR_RENDER_DB_PASSWORD>`
- **`OPENAI_API_KEY`**: `sk-mavis-seed-demo-key-2026`
- **`JWT_SECRET`**: `mavis-seed-jwt-secret-key-2026`

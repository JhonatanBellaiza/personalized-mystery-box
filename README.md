# Project Statement: Personalized Clothing Mystery Box Subscription Service

## Objective
To create a web platform that provides a subscription-based mystery box service, offering personalized clothing selections based on each customer’s style preferences. The service will feature three membership levels, enabling customers to receive monthly shipments of curated clothing items and accessories.

## Description
The subscription service offers three distinct membership tiers:

1. **Upper Body Box** – $29.99 per month:  
   Includes personalized selections of tops, such as T-shirts, blouses, and sweatshirts, based on user style preferences and color choices.

2. **Lower Body Box** – $39.99 per month:  
   Provides customized bottoms, including pants, jeans, and skirts, suited to the customer’s chosen styles, fits, and color preferences.

3. **Full Combo Box** – $59.99 per month:  
   A complete package that includes items from both upper and lower body categories, along with two or more accessories (e.g., belts, scarves, hats) to enhance the customer’s style.

The system curates items each month based on a style questionnaire completed during registration, which captures details such as favorite colors, disliked colors, preferred clothing fits, and personal style preferences. Each monthly box will be unique, with no repeated items, ensuring freshness and variety. Customers can manage their subscriptions, view past shipments, and update their style preferences through their account portal.

## Features
1. **Customer Registration and Style Questionnaire**: Customers sign up, complete a style profile, and choose a membership tier.
2. **Membership Tiers**: Three subscription options tailored to customer needs and budget.
3. **Automated Monthly Curation**: The system generates a unique monthly box based on each customer’s preferences and past box history to avoid item repetition.
4. **Admin Dashboard**: Enables administrators to view and manage customer profiles, oversee curated items, and review box content before shipment.

## Manager Functionalities
- **Customer Management**: View and edit customer profiles, monitor active and inactive subscriptions, and assist with profile updates.
- **Inventory Control**: Track item availability to ensure proper selection for each mystery box.
- **Subscription Box Assembly**: Preview curated boxes and make manual adjustments if needed.
- **Order Fulfillment**: Mark boxes as shipped, track deliveries, and handle any order-related issues.

## Pricing Strategy
The pricing reflects a balance between quality and affordability, aimed at fashion-conscious individuals seeking variety and convenience. The Full Combo Box offers the best value with a broader selection of items, targeting those interested in complete looks each month.

## Impact
This service combines fashion personalization with the excitement of mystery boxes. It caters to customers looking for curated, stylish apparel while allowing them to discover new trends and expand their wardrobe monthly.

# Architecture Diagram - Monolythic Architecture

## 1. Frontend Layer
- **React Application**: Hosted on AWS S3 and CloudFront, optimized for global access.
- **Connection**: Direct HTTPS connection to **API Gateway**.

## 2. API Gateway Layer
- **Component**: AWS API Gateway
- **Purpose**: Manages external access, handling:
  - Routing: Directs requests to the appropriate backend endpoint.
  - Authentication & Authorization: Ensures secure access.
  - Rate Limiting: Controls traffic to prevent overloading the backend.

## 3. Load Balancer Layer
- **Component**: AWS Elastic Load Balancer (ELB)
- **Purpose**: Balances traffic across multiple Docker containers of the monolithic backend service.
- **Connection**: 
  - Connected to API Gateway, which routes requests to the Load Balancer.
  - Load Balancer distributes requests across backend instances.

## 4. Backend Service Layer (Monolithic with Docker)
- **Technology**: Java Spring Boot application, now Dockerized and deployed on ECS or EC2 instances.
- **Dockerization**:
  - Each module (User, Inventory, Order, and Authentication) is packaged within the same Docker container as part of a single monolithic service.
  - A Docker image is created for the monolithic application, allowing consistent deployment across instances.
- **Components within Backend**:
  - **User Module**: Manages user profiles.
  - **Inventory Module**: Manages product data.
  - **Order Module**: Handles orders.
  - **Subscription Module**: Handles Subscriptions.
  - **Billing Module**: Handles Billing module.
  - **Authentication (JWT)**: Validates user authentication using JWT.
- **Deployment**:
  - Hosted on **AWS ECS (Elastic Container Service)** with Docker containers running the monolithic backend.
  - ECS allows orchestration and scaling of Docker containers based on traffic.
- **Connection**:
  - All requests are handled internally by the single Dockerized monolithic service, simplifying module communication.

## 5. Database Layer
- **RDS for MySQL**: Stores main data (users, orders).
- **DynamoDB**: Stores inventory data (if necessary).
- **Security**: AWS Secrets Manager handles credentials securely.

## 6. Management and Monitoring Tools
- **Admin Dashboard**: Part of the monolithic backend, accessible internally.
- **Monitoring and Logging**: AWS CloudWatch monitors and logs Docker container performance.

## Docker Integration Summary
- **Docker Image**: Package the monolithic Java Spring Boot app as a Docker image.
- **AWS ECS**: Deploy this Docker image on ECS, leveraging ECS to manage and scale containers based on load.
- **Scalability**: The Load Balancer can route requests to multiple instances of this Dockerized monolithic service.
- **Consistency and Portability**: Docker ensures that the application runs consistently across environments.

## Full Monolithic Architecture with Docker Summary
1. **Frontend (React App)** → **API Gateway**
2. **API Gateway** → **Load Balancer (ELB)**
3. **Load Balancer (ELB)** → **Monolithic Backend (Dockerized Java Spring Boot on ECS)**
4. **Monolithic Backend (Docker)** ↔ **Databases (RDS and DynamoDB)**

![Architecture Diagram](images/architecture2.png)



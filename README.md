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

# Architecture Diagram

## Frontend Layer
- **Component**: React Application
- **Hosted on**: AWS S3 and CloudFront (for faster global access)
- **Connection**: HTTPS to API Gateway

## API Gateway
- **Purpose**: Manages external access to microservices, including authentication, authorization, and routing
- **Connection**: Connects to Load Balancer, validates tokens from Authentication Service
- **Hosted on**: AWS API Gateway

## Authentication Service
- **Purpose**: Manages user authentication and issues tokens
- **Hosted on**: JWT-based system on ECS
- **Connection**: Connected to API Gateway for token validation

## Load Balancer
- **Component**: AWS Elastic Load Balancer (ELB)
- **Purpose**: Balances incoming requests across multiple instances of microservices
- **Connection**: Routes requests to microservices securely using HTTPS

## Microservices Layer
- **Technologies**: Java Spring Boot
- **Hosted on**: Docker containers in AWS ECS
- **Services**:
  - **User Service**: Handles customer data
  - **Inventory Service**: Manages product and inventory info
  - **Order Service**: Manages orders and subscription fulfillment
- **Connections**: Each service connects to its respective database and can interact with other services as needed

## Database Layer
- **Components**: RDS for MySQL and DynamoDB (Inventory data)
- **Hosted on**: AWS RDS, AWS DynamoDB
- **Connection**: Direct access from microservices; secure using AWS Secrets Manager for credentials

## Management Tools
- **Admin Dashboard**: Internal tool for viewing and managing customer profiles, order statuses, and inventory
- **Monitoring and Logging**: CloudWatch

## Connections and Security Details
- **HTTPS Connections**: Use HTTPS for all external and inter-service communication to ensure encrypted data transfers
- **Authentication Tokens**: JWT tokens are used for validating each request from API Gateway to secure microservices
- **IAM Roles**: Implement strict IAM policies for accessing AWS services and databases
- **Data Encryption**: Encrypt all databases to protect data at rest

![Architecture Diagram](images/architecture.png)



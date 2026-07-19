# ShopSphere - E-Commerce Backend

ShopSphere is an e-commerce backend application developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

The application provides REST APIs for managing users, categories, products, wishlists, reviews, carts, cart items, orders, order items, and payments.

---

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* REST API
* Postman

---

# API Endpoints

## 1. User Controller

Base URL:

`/users`

| Method | Endpoint      | Description       |
| ------ | ------------- | ----------------- |
| POST   | `/users`      | Add user/users    |
| GET    | `/users`      | Get all users     |
| GET    | `/users/{id}` | Get user by ID    |
| DELETE | `/users/{id}` | Delete user by ID |

---

## 2. Category Controller

Base URL:

`/category`

| Method | Endpoint         | Description           |
| ------ | ---------------- | --------------------- |
| POST   | `/category`      | Add category          |
| GET    | `/category`      | Get all categories    |
| GET    | `/category/{id}` | Get category by ID    |
| DELETE | `/category/{id}` | Delete category by ID |

---

## 3. Product Controller

Base URL:

`/products`

| Method | Endpoint         | Description          |
| ------ | ---------------- | -------------------- |
| POST   | `/products`      | Add product/products |
| GET    | `/products`      | Get all products     |
| GET    | `/products/{id}` | Get product by ID    |
| DELETE | `/products/{id}` | Delete product by ID |

### Note

While adding a product, the provided `cat_id` must belong to an existing category.

---

## 4. Wishlist Controller

Base URL:

`/wishlist`

| Method | Endpoint         | Description              |
| ------ | ---------------- | ------------------------ |
| POST   | `/wishlist`      | Add product to wishlist  |
| GET    | `/wishlist`      | Get all wishlist records |
| GET    | `/wishlist/{id}` | Get wishlist by ID       |
| DELETE | `/wishlist/{id}` | Delete wishlist by ID    |

### Note

The `user_id` and `prod_id` must exist before adding a product to the wishlist.

---

## 5. Review Controller

Base URL:

`/reviews`

| Method | Endpoint        | Description         |
| ------ | --------------- | ------------------- |
| POST   | `/reviews`      | Add product review  |
| GET    | `/reviews`      | Get all reviews     |
| GET    | `/reviews/{id}` | Get review by ID    |
| DELETE | `/reviews/{id}` | Delete review by ID |

---

## 6. Cart Controller

Base URL:

`/cart`

| Method | Endpoint     | Description                 |
| ------ | ------------ | --------------------------- |
| POST   | `/cart`      | Create cart with cart items |
| GET    | `/cart`      | Get all carts               |
| GET    | `/cart/{id}` | Get cart by ID              |
| DELETE | `/cart/{id}` | Delete cart by ID           |

### Cart Operations

While creating a cart:

* The user is checked using `user_id`.
* Each product is checked using `prod_id`.
* Product stock availability is checked.
* Products with sufficient stock are added.
* Products with insufficient stock are not added.
* Adding a product to the cart does **not** reduce product stock.

`Cart` and `CartItems` use a One-to-Many relationship with `CascadeType.ALL`.

Therefore, saving a Cart can also save its associated CartItems.

---

## 7. Cart Items Controller

Base URL:

`/cartitems`

| Method | Endpoint          | Description            |
| ------ | ----------------- | ---------------------- |
| GET    | `/cartitems`      | Get all cart items     |
| GET    | `/cartitems/{id}` | Get cart item by ID    |
| DELETE | `/cartitems/{id}` | Delete cart item by ID |

Cart items can also be fetched based on their associated Cart ID using a custom repository query.

---

## 8. Order Controller

Base URL:

`/orders`

| Method | Endpoint       | Description                   |
| ------ | -------------- | ----------------------------- |
| POST   | `/orders`      | Create order with order items |
| GET    | `/orders`      | Get all orders                |
| GET    | `/orders/{id}` | Get order by ID               |
| DELETE | `/orders/{id}` | Delete order by ID            |

### Order Operations

While creating an order:

1. The user is validated using `user_id`.
2. Each product is validated using `prod_id`.
3. Product stock availability is checked.
4. Products with sufficient stock are added to the order.
5. Products with insufficient stock are not added.
6. The total order amount is calculated automatically.
7. Product stock is reduced based on the ordered quantity.
8. The Order and associated OrderItems are saved.

`Order` and `OrderItems` use a One-to-Many relationship with `CascadeType.ALL`.

Therefore:

`orderRepo.save(order)`

can save both the Order and its associated OrderItems.

### Deleting an Order

When an order is deleted:

* All associated OrderItems are removed.
* The quantity of each deleted OrderItem is restored to the corresponding product's available stock.

---

## 9. Order Items Controller

Base URL:

`/orderitems`

| Method | Endpoint           | Description             |
| ------ | ------------------ | ----------------------- |
| GET    | `/orderitems`      | Get all order items     |
| GET    | `/orderitems/{id}` | Get order item by ID    |
| DELETE | `/orderitems/{id}` | Delete order item by ID |

OrderItems can also be fetched using their associated `order_id` through a custom repository query.

---

## 10. Payment Controller

Base URL:

`/payment`

| Method | Endpoint        | Description          |
| ------ | --------------- | -------------------- |
| POST   | `/payment`      | Add payment          |
| GET    | `/payment`      | Get all payments     |
| GET    | `/payment/{id}` | Get payment by ID    |
| DELETE | `/payment/{id}` | Delete payment by ID |

### Payment Operations

While creating a payment:

* The associated `order_id` must exist.
* The payment amount is automatically taken from the Order's `totalAmount`.
* The payment status is updated.
* The payment date is automatically generated using `@PrePersist`.
* The payment is associated with the corresponding Order.

Example request structure:

```json
{
  "order": {
    "order_id": 1
  },
  "payment_method": "UPI",
  "payment_status": "SUCCESS"
}
```

The following fields do not need to be manually provided:

* `payment_id` - Automatically generated.
* `amount` - Taken from the Order total amount.
* `payment_date` - Automatically generated.

---

# Important Implementation Notes

## Map-Based API Responses

Some service methods use:

`Map<String, Object>`

This allows the API to return different types of values in a single response.

For example:

```text
message     -> String
order       -> Order Object
not_added   -> List<String>
```

Because the value type is `Object`, the Map can contain Strings, Lists, entities, and other objects.

This is useful when an operation needs to return both successful and unsuccessful results.

For example, while creating an Order:

* Valid products are added to the Order.
* Products with insufficient stock are returned in the `not_added` list.

---

## Cascade Operations

`CascadeType.ALL` is used for parent-child relationships such as:

```text
Cart
  |
  +-- CartItems

Order
  |
  +-- OrderItems
```

Saving the parent can automatically save the associated child entities.

For example:

`cartRepo.save(cart)`

can save the Cart and CartItems.

Similarly:

`orderRepo.save(order)`

can save the Order and OrderItems.

---

## JSON Infinite Recursion

Bidirectional entity relationships may cause infinite JSON responses.

Example:

```text
Order
  -> OrderItems
      -> Order
          -> OrderItems
              -> ...
```

`@JsonIgnore` is used on the required side of a relationship to prevent infinite JSON serialization.

Care should be taken when using `@JsonIgnore` because ignored fields may also affect incoming JSON mapping depending on where the annotation is placed.

---

## Product Stock Management

Product stock is handled differently for Cart and Order operations.

### Cart

Adding a product to the Cart does not reduce stock.

```text
Add to Cart
    |
    +-- Check stock
    |
    +-- Add CartItem
    |
    +-- Stock remains unchanged
```

### Order

When an Order is successfully created:

```text
Create Order
    |
    +-- Check stock
    |
    +-- Add valid OrderItems
    |
    +-- Calculate total amount
    |
    +-- Reduce product stock
```

If an Order is deleted, the ordered quantities are restored to the available product stock.

---

## Repository `save()` Method

The `save()` method can be used for both inserting and updating entities.

### New Entity

If the entity is new, `save()` inserts a new database record.

### Existing Entity

If an existing entity is fetched, modified, and saved, the existing database record is updated.

Example:

```text
Fetch Product
    |
    +-- Change stockAvailable
    |
    +-- productRepo.save(product)
    |
    +-- Existing Product is updated
```

A new primary key is not generated when updating an existing entity.

---

## Testing APIs Using Postman

Before performing POST operations involving entity relationships, make sure the referenced records already exist.

For example:

* `user_id` must exist before creating a Cart or Order.
* `cat_id` must exist before adding a Product.
* `prod_id` must exist before adding CartItems, OrderItems, Reviews, or Wishlist records.
* `order_id` must exist before adding a Payment.

When using an endpoint containing `{id}`, the ID represents the primary key of that resource.

Examples:

`GET /users/2`

Returns the User with `user_id = 2`.

`GET /orders/1`

Returns the Order with `order_id = 1`.

`GET /payment/1`

Returns the Payment with `payment_id = 1`.

`DELETE /orders/1`

Deletes the Order with `order_id = 1`.

---

# Database Relationships

<img width="1734" height="845" alt="Screenshot 2026-07-17 174223" src="https://github.com/user-attachments/assets/4fbfff0a-b52f-46d2-8007-c5ce0ca3b12c" />

The main relationships used in the project are:

```text
User
 |
 +-- Cart
 |    |
 |    +-- CartItems
 |
 +-- Orders
 |    |
 |    +-- OrderItems
 |    |
 |    +-- Payments
 |
 +-- Wishlist
 |
 +-- Reviews

Category
 |
 +-- Products

Products
 |
 +-- CartItems
 |
 +-- OrderItems
 |
 +-- Wishlist
 |
 +-- Reviews
```

---

# Summary

ShopSphere provides REST APIs for managing the major operations of an e-commerce system, including:

* User management
* Product and category management
* Wishlist management
* Product reviews
* Shopping cart management
* Order processing
* Stock management
* Payment processing

The application uses Spring Data JPA and Hibernate for database operations and entity relationships, while MySQL is used for data storage.

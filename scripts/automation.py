import requests
import json

BASE_URL = "http://host.docker.internal:8080"

# Get Available Pizzas
def get_menu():
    response = requests.get(f"{BASE_URL}/menu/pizzas")
    if response.status_code == 200:
        print("Menu:", response.json())
        return response.json()
    else:
        print("Failed to get menu:", response.text)
        return []

# Add a Pizza to the Menu
def add_pizza(name, price, ingredients):
    pizza_data = {
        "name": name,
        "price": price,
        "ingredients": ingredients
    }
    response = requests.post(f"{BASE_URL}/menu/addPizza", json=pizza_data)
    if response.status_code == 200:
        print("Pizza Added:", response.text)
    else:
        print("Failed to add pizza:", response.text)

# Update Pizza Price
def update_pizza_price(name, new_price):
    params = {"name": name, "price": new_price}
    response = requests.put(f"{BASE_URL}/menu/updatePrice", params=params)
    if response.status_code == 200:
        print("Price Updated:", response.text)
    else:
        print("Failed to update price:", response.text)

# Check Inventory
def check_inventory():
    response = requests.get(f"{BASE_URL}/inventory")
    if response.status_code == 200:
        print("Inventory:", response.json())
        return response.json()
    else:
        print("Failed to check inventory:", response.text)
        return {}

# Order Pizza
def order_pizza(pizzas, sides=[]):
    formatted_pizzas = [
        {
            "name": pizza["name"],
            "type": pizza.get("type", "VEGETARIAN"),
            "size": pizza.get("size", "REGULAR"),
            "basePrice": pizza.get("basePrice", 10.0),
            "crust": pizza.get("crust", "WHEAT_THIN_CRUST"),
            "toppings": [
                {"name": topping["name"], "type": topping["type"], "price": topping["price"]}
                for topping in pizza.get("toppings", [])
            ]
        }
        for pizza in pizzas
    ]
    
    formatted_sides = [{"name": side["name"], "price": side["price"]} for side in sides]
    
    order_data = {"pizzas": formatted_pizzas, "sides": formatted_sides}
    print(order_data)
    response = requests.post(f"{BASE_URL}/order/place", json=order_data)
    
    if response.status_code == 200:
        print("Order Success:", response.json())
    else:
        print("Order Failed:", response.text)

# Refill Inventory
def refill_inventory(item_name, quantity):
    refill_data = {"name": item_name, "quantity": quantity}
    response = requests.post(f"{BASE_URL}/inventory/restock", json=refill_data)
    if response.status_code == 200:
        print("Inventory Refilled:", response.json())
    else:
        print("Refill Failed:", response.text)

if __name__ == "__main__":
    menu = get_menu()
    
    if not menu:
        print("No pizzas found! Adding a new pizza...")
        add_pizza("Pepperoni", 12.99, ["Cheese", "Pepperoni", "Tomato Sauce"])
        menu = get_menu()
    if menu:
        pizza_to_order = menu[0]
        print(f"Ordering {pizza_to_order['name']}...")
        order_pizza([
            {
                "name": pizza_to_order["name"],
                "type": "VEGETARIAN",
                "size": "REGULAR",
                "basePrice": 10.0,
                "crust": "WHEAT_THIN_CRUST",
                "toppings": [{"name": "Olives", "type": "VEG", "price": 1.5}]
            }
        ], [])
    
    inventory_before = check_inventory()
    
    # Refill inventory if needed
    for item, stock in inventory_before.items():
        if stock < 5:
            print(f"Refilling {item}...")
            refill_inventory(item, 10)
    
    check_inventory()
    
    # Updating price for a pizza
    if menu:
        update_pizza_price(menu[0]["name"], 14.99)

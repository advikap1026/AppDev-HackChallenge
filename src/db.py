from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class User(db.Model):
    __tablename__ = "users"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(255), unique=True, nullable=True)

    items = db.relationship("Item", back_populates="owner")
    shopping_list_items = db.relationship(
        "ShoppingListItem", back_populates="owner"
    )

    location = db.Column(db.String(20), nullable=False, index=True)

    quantity = db.Column(db.Float, nullable=False, default=1)
    unit = db.Column(db.String(40), nullable=True)
    category = db.Column(db.String(80), nullable=True)
    notes = db.Column(db.Text, nullable=True)

    expiration_date = db.Column(
        db.Date, nullable=True, index=True
    )

    running_low = db.Column(
        db.Boolean, nullable=False, default=False, index=True
    )

    owner = db.relationship("User", back_populates="items")

    shopping_list_entries = db.relationship(
        "ShoppingListItem",
        back_populates="item",
        cascade="all, delete-orphan",
    )

class ShoppingListItem(db.Model):
    __tablename__ = "shopping_list"

    id = db.Column(db.Integer, primary_key=True)

    name = db.Column(db.String(150), nullable=False)
    quantity = db.Column(db.Float, nullable=False, default=1)
    unit = db.Column(db.String(40), nullable=True)

    # True when the user has checked off the item
    checked = db.Column(
        db.Boolean, nullable=False, default=False, index=True
    )

    notes = db.Column(db.Text, nullable=True)


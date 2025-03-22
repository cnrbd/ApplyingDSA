

package main;

/**
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 */

public class AVLPlayerNode {
    private Player data;
    private double value;
    private AVLPlayerNode parent;
    private AVLPlayerNode leftChild;
    private AVLPlayerNode rightChild;
    private int rightWeight; // keeps track of the amount of nodes in the right subtree
    private int balanceFactor;
    private int height;

    public AVLPlayerNode(Player data, double value) {
        this.data = data;
        this.value = value;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.rightWeight = 0;
        this.balanceFactor = 0;

    }

    // This should return the new root of the tree
    // make sure to update the balance factor and right weight
    // and use rotations to maintain AVL condition
    /**
     * This method is used to insert a new player into the AVL tree
     * O(logn)
     *
     * @param newGuy the new player that is going to be inserted
     * @param value  the value of the player
     * @return the new root of the tree
     */
    public AVLPlayerNode insert(Player newGuy, double value) {

        AVLPlayerNode newNode = findInsertSpot(newGuy, value);
        // after inserting the node:

        AVLPlayerNode curr = newNode;
        AVLPlayerNode prev = null;

        while (curr != null) {
            // bf = 2 -> left heavy so rotate right
            curr.height = 1 + curr.calculateHeight();
            curr.balanceFactor = curr.calculateBalanceFactor();
            if (curr.balanceFactor > 1) {
                // left right imbalance
                if (curr.leftChild.balanceFactor < 0) {
                    curr.leftChild.rotateLeft();
                    curr.rotateRight();
                }
                // normal case
                else if (curr.leftChild.balanceFactor >= 0) {
                    curr.rotateRight();
                }
                // bf = -2 -> right heavy so rotate left
            } else if (curr.balanceFactor < -1) {
                // right left imbalance
                if (curr.rightChild.balanceFactor > 0) {
                    curr.rightChild.rotateRight();
                    curr.rotateLeft();
                }
                // normal case
                else if (curr.rightChild.balanceFactor <= 0) {
                    curr.rotateLeft();
                }
            }
            prev = curr;
            curr = curr.parent;
        }

        return prev;
    }

    /**
     * This method is used to find the spot to insert the new player
     * O(logn)
     *
     * @param newGuy   the player to insert
     * @param newValue the value of the player
     * @return the new node that is inserted
     */
    public AVLPlayerNode findInsertSpot(Player newGuy, double newValue) {

        AVLPlayerNode newNode = null;

        if (newValue < this.value) {
            if (this.leftChild == null) {

                newNode = new AVLPlayerNode(newGuy, newValue);
                this.leftChild = newNode;
                this.leftChild.parent = this;
            } else {

                newNode = this.leftChild.findInsertSpot(newGuy, newValue);
            }
        } else if (newValue >= this.value) {
            this.rightWeight++; // increment the right weight
            if (this.rightChild == null) {

                newNode = new AVLPlayerNode(newGuy, newValue);
                this.rightChild = newNode;
                this.rightChild.parent = this;
            } else {

                newNode = this.rightChild.findInsertSpot(newGuy, newValue);
            }
        }

        // update height
        this.height = 1 + this.calculateHeight();

        // update bf
        this.balanceFactor = this.calculateBalanceFactor();

        return newNode;
    }

    // This should return the new root of the tree
    // remember to update the right weight
    /**
     * This method is used to delete a player from the AVL tree
     * o(logn)
     *
     * @param value the value of the player to be deleted
     * @return the new root of the tree
     */
    public AVLPlayerNode delete(double value) {
        // 1st case - delete node with no children
        // 2nd case - delete node with one child
        // 3rd case - delete node with two children

        AVLPlayerNode nodeToDelete = search(value);
        // node does not exist
        if (nodeToDelete == null) {
            return this;
        }

        AVLPlayerNode curr = null;

        // two children and sucessor can never either have one or no children
        if (nodeToDelete.leftChild != null && nodeToDelete.rightChild != null) {
            AVLPlayerNode successor = nodeToDelete.getSucessor();

            // sucessor is always on the right;
            nodeToDelete.data = successor.data;
            nodeToDelete.value = successor.value;

            curr = bstDelete(successor);
        }
        // if there is one or no children
        else {
            curr = bstDelete(nodeToDelete);
        }

        AVLPlayerNode rightWeightEdit = curr;

        AVLPlayerNode prev = null;

        while (rightWeightEdit.parent != null
                && rightWeightEdit == rightWeightEdit.parent.rightChild) {
            rightWeightEdit.rightWeight--;
            prev = rightWeightEdit;
            rightWeightEdit = rightWeightEdit.parent;

        }

        if (rightWeightEdit.parent == null && prev == rightWeightEdit.rightChild) {
            rightWeightEdit.rightWeight--;
        }
        prev = null;

        while (curr != null) {
            curr.height = 1 + curr.calculateHeight();
            curr.balanceFactor = curr.calculateBalanceFactor();

            if (curr.balanceFactor > 1) {
                // left right imbalance
                if (curr.leftChild.balanceFactor < 0) {
                    curr.leftChild.rotateLeft();
                    curr.rotateRight();
                }
                // normal case
                else if (curr.leftChild.balanceFactor >= 0) {
                    curr.rotateRight();
                }
                // bf = -2 -> right heavy so rotate left
            } else if (curr.balanceFactor < -1) {
                // right left imbalance
                if (curr.rightChild.balanceFactor > 0) {
                    curr.rightChild.rotateRight();
                    curr.rotateLeft();
                }
                // normal case
                else if (curr.rightChild.balanceFactor <= 0) {
                    curr.rotateLeft();
                }
            }

            prev = curr;
            curr = curr.parent;
        }

        return prev;
    }

    /**
     * This method deletes a node like bst
     * O(1)
     *
     * @param node the node to be deleted
     * @return the parent of the node that is deleted
     */
    public AVLPlayerNode bstDelete(AVLPlayerNode node) {
        AVLPlayerNode child;
        if (node.leftChild != null) {
            child = node.leftChild;
        } else {
            child = node.rightChild;
        }
        // case when node is root but only will be callled when it has 1 or 0 children
        if (node.parent == null) {
            if (child != null) {
                child.parent = null;
            }
            return child;
        }
        if (node == node.parent.leftChild) {

            node.parent.leftChild = child;
        } else {
            node.parent.rightChild = child;
            // node.parent.rightWeight--;
        }

        // put child parent as the deleted node parent
        if (child != null) {
            child.parent = node.parent;
        }
        return node.parent;

    }

    /**
     * This method is used to search for a player with the value
     * O(log n)
     *
     * @param value the value to search
     * @return the node with that value
     */
    public AVLPlayerNode search(double value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            // if value is less than so search left
            if (this.leftChild == null) {
                return null;
            }
            return this.leftChild.search(value);
        } else if (value > this.value) {
            if (this.rightChild == null) {
                return null;
            }
            return this.rightChild.search(value);
        } else {
            return null;
        }
    }

    /**
     * This method is used to calculate the balance factor of the node
     * O(1)
     *
     * @return the balance factor of the node
     */
    public int calculateBalanceFactor() {
        int left_height = -1;
        if (this.leftChild != null) {
            left_height = this.leftChild.height;
        }

        int right_height = -1;
        if (this.rightChild != null) {
            right_height = this.rightChild.height;
        }

        return left_height - right_height;
    }

    /**
     * This method is used to calculate the height of the node
     * O(1)
     *
     * @return the height of the node
     */
    public int calculateHeight() {
        int left_height = -1;
        if (this.leftChild != null) {
            left_height = this.leftChild.height;
        }

        int right_height = -1;
        if (this.rightChild != null) {
            right_height = this.rightChild.height;
        }

        if (left_height > right_height) {
            return left_height;
        } else {
            return right_height;
        }
    }

    /**
     * Method that rotaes the tree right
     * O(1)
     */
    private void rotateRight() {

        AVLPlayerNode left = this.leftChild;
        // System.out.println("left child: " + left.data.getName());
        this.leftChild = left.rightChild;

        if (left.rightChild != null) {
            left.rightChild.parent = this;
        }
        left.parent = this.parent;

        if (this.parent == null) {
            // set left as root
            left.parent = null;
        } else if (this == this.parent.rightChild) {
            this.parent.rightChild = left;
        } else {
            this.parent.leftChild = left;
        }
        left.rightChild = this;
        left.rightWeight += this.rightWeight + 1;
        this.parent = left;
        if (this.rightChild != null) {
            this.rightWeight = this.rightChild.rightWeight + 1;
        } else {
            this.rightWeight = 0;
        }

        this.height = 1 + this.calculateHeight();
        this.balanceFactor = this.calculateBalanceFactor();
        left.height = 1 + left.calculateHeight();

        left.balanceFactor = left.calculateBalanceFactor();

    }

    // remember to maintain rightWeight
    /**
     * Method that rotates the tree left
     * O(1)
     */
    private void rotateLeft() {

        AVLPlayerNode right = this.rightChild;

        this.rightChild = right.leftChild;

        if (right.leftChild != null) {
            right.leftChild.parent = this;
        }

        right.parent = this.parent;

        if (this.parent == null) {
            // set the root as right
            right.parent = null;
        } else if (this == this.parent.leftChild) {
            this.parent.leftChild = right;
        } else {
            this.parent.rightChild = right;
        }
        right.leftChild = this;
        this.parent = right;

        // right.rightWeight does not change

        if (this.rightChild != null) {
            this.rightWeight = this.rightChild.rightWeight + 1;
        } else {
            this.rightWeight = 0;
        }

        this.height = 1 + this.calculateHeight();
        this.balanceFactor = this.calculateBalanceFactor();
        right.height = 1 + right.calculateHeight();

        right.balanceFactor = right.calculateBalanceFactor();
    }

    // this should return the Player object stored in the node with this.value ==
    // value
    /**
     * This method is used to get the player with the value
     * O(log n)
     *
     * @param value the value to search
     * @return the player with that value or null if not found
     */
    public Player getPlayer(double value) {
        // search for the player according to the value
        if (value == this.value) {
            return this.data;
        } else if (value < this.value) {
            // if value is less than so search left
            if (this.leftChild == null) {
                return null;
            }
            return this.leftChild.getPlayer(value);
        } else if (value > this.value) {
            if (this.rightChild == null) {
                return null;
            }
            return this.rightChild.getPlayer(value);
        } else {
            return null;
        }
    }

    // this should return the rank of the node with this.value == value
    /**
     * O(log n)
     * This method is used to get the rank of the player with the value
     *
     * @param value the value to search
     * @return the rank of the player
     */
    public int getRank(double value) {
        // search for the player according to the value
        if (value == this.value) {
            return this.rightWeight + 1;
        } else if (value < this.value) {
            // if value is less than so search left
            if (this.leftChild == null) {
                return -1;
            }
            int leftChildRank = this.leftChild.getRank(value);
            if (leftChildRank == -1) {
                return -1;
            } else {
                return leftChildRank + this.rightWeight + 1;
            }

        } else if (value > this.value) {
            if (this.rightChild == null) {
                return -1;
            }
            return this.rightChild.getRank(value);
        } else {
            return -1;
        }
    }

    // this should return the tree of names with parentheses separating subtrees
    // eg "((bob)alice(bill))"
    /**
     * O(n)
     * This method is used to get the tree string
     *
     * @return the tree string
     */
    public String treeString() {

        String result = "(";

        if (this.leftChild != null) {
            result += this.leftChild.treeString();
        }

        result += this.data.getName();

        if (this.rightChild != null) {
            result += this.rightChild.treeString();
        }

        result += ")";

        return result;
    }

    // this should return a formatted scoreboard in descending order of value
    // see example printout in the pdf for the command L
    /**
     * O(n)
     * This method is used to get the scoreboard
     *
     * @return the scoreboard
     */
    public String scoreboard() {
        return "NAME\tID  SCORE\n" + this.rightInOrder();
    }

    /**
     * This method is used to get the right in order of the tree
     * O(n)
     *
     * @return the right in order of the tree
     */
    public String rightInOrder() {
        String result = "";

        if (this.rightChild != null) {
            result += this.rightChild.rightInOrder();
        }

        result += this.data.getName() + "\t" + this.data.getID() + "  " + String.format("%.2f", this.data.getELO())
                + "\n";

        if (this.leftChild != null) {
            result += this.leftChild.rightInOrder();
        }
        return result;
    }

    /**
     * This method is used to get the balance factor of the node
     * O(1)
     *
     * @return the balance factor of the node
     */
    public int getBalanceFactor() {
        return this.balanceFactor;
    }

    /**
     * This method is used to get the RightWeight of the node
     * O(1)
     *
     * @return the RightWeight of the node
     */
    public int getRightWeight() {
        return this.rightWeight;
    }

    /**
     * This method is used to get the min of the tree
     * O(logn)
     *
     * @return the min of the tree
     */
    public AVLPlayerNode min() {
        AVLPlayerNode curr = this;
        while (curr.leftChild != null) {
            curr = curr.leftChild;
        }
        return curr;
    }

    /**
     * This method is used to get the sucessof of a node tree
     * O(logn)
     *
     * @return the sucessor of the node
     */
    public AVLPlayerNode getSucessor() {
        if (this.rightChild != null) {
            return this.rightChild.min();
        } else {
            AVLPlayerNode sucessor = this.parent;
            AVLPlayerNode curr = this;
            while (sucessor != null && curr == sucessor.rightChild) {
                curr = sucessor;
                sucessor = sucessor.parent;
            }
            return sucessor;
        }
    }

}

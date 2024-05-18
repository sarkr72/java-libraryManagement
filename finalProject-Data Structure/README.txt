User:
	LinkedList and TreeMap for user borrowing history, because I put all reading history in linked list and it
	allows duplicate values. But does not allow duplicate, so one user can not have same book with 2 copies. 
	Searching borrowing book by linked list gives O(n).
	searching current borrowing book treemap gives logn;
	
UserBag: All the users are in a HashMap and gave the size of 50000.
			 	Because searching user using hashMap by userName gives big o (1).
		Then I have LinkedList and TreeMap for borrowing history for all the users.
		Searching borrowed books by treeMap will give big O(logn) and linkedList O(n).
		To search by isbn, treeMap would be better.
		
BookBag: 
		TreeMap contains all the books that a librarian adds into the library system.
		Then linkedList and treeMap for borrowing history.
		LinkedList because it can contain same book with many copies.
		Search by isbn using treeMap gives big O(logn).
		search by linkedList has big O(n).
		
Admin:
		Admin or librabrian has linkedlist and treemap for borrowing history.
		linkedList will contain same book with many copies.
		Treemap allows fast search by isbn which is big o(logn).

AdminBag:
		It has HashMap for all the admins or librabrians.
		big o for searching by username (1).

Utilities: I used hashSet to store dictionary.
			becauase it has big o(1) search by word.
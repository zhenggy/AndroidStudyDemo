##Java中Vector ArrayList LinkedList之间的区别与联系

### ArrayList :
List 接口的大小可变数组的实现。实现了所有可选列表操作，并允许包括 null 在内的所有元素。除了实现 List 接口外，此类还提供一些方法来操作内部用来存储列表的数组的大小。（此类大致上等同于 Vector 类，除了**此类是不同步的** 。）

size、isEmpty、get、set、iterator 和 listIterator 操作都以固定时间运行。add 操作以分摊的固定时间 运行，也就是说，**添加 n 个元素需要 O(n) 时间**。其他所有操作都以线性时间运行（大体上讲）。与用于 LinkedList 实现的常数因子相比，此实现的常数因子较低。

每个 ArrayList 实例都有一个容量。该容量是指用来存储列表元素的数组的大小。它总是至少等于列表的大小。随着向 ArrayList 中不断添加元素，其容量也自动增长。并未指定增长策略的细节，因为这不只是添加元素会带来分摊固定时间开销那样简单。

在添加大量元素前，应用程序可以使用 ensureCapacity 操作来增加 ArrayList 实例的容量。这可以减少递增式再分配的数量。

---

###Vector :
Vector 类可以实现可增长的对象数组。与数组一样，它包含可以使用整数索引进行访问的组件。但是，Vector 的大小可以根据需要增大或缩小，以适应创建 Vector 后进行添加或移除项的操作。 

每个向量会试图通过维护 capacity 和 capacityIncrement 来优化存储管理。capacity 始终至少应与向量的大小相等；这个值通常比后者大些，因为随着将组件添加到向量中，其存储将按 capacityIncrement 的大小增加存储块。应用程序可以在插入大量组件前增加向量的容量；这样就减少了增加的重分配的量。 

---

###LinkedList :
List 接口的链接列表实现。实现所有可选的列表操作，并且允许所有元素（包括 null）。除了实现 List 接口外，LinkedList 类还为在列表的开头及结尾 get、remove 和 insert 元素提供了统一的命名方法。这些操作允许将链接列表用作**堆栈、队列或双端队列**。

此类实现 Deque 接口，为 add、poll 提供先进先出队列操作，以及其他堆栈和双端队列操作。

所有操作都是按照双重链接列表的需要执行的。在列表中编索引的操作将从开头或结尾遍历列表（从靠近指定索引的一端）。

注意，此实现不是同步的。如果多个线程同时访问一个链接列表，而其中至少一个线程从结构上修改了该列表，则它必须 保持外部同步。


---

**ArrayList 与 Vector**：

ArrayList 与 Vector 基本相同，不同的是Vector是同步的，而ArrayList不是同步的，所以ArrayList速度会比较快。


----

**ArrayList 与 LinkedList**：

ArrayList 是通过数组实现的，所以随机访问的速度是O(1)，而从其中插入、删除元素的话会复制数组，开销较大。

LinkedList 是通过链表实现的，所以插入、删除元素会很快，但是随机访问的速度就较慢了。可以当作堆栈、队列或双端队列使用。

	ArrayList的删除元素的方法:
	public E remove(int index) {
	        rangeCheck(index);
	
	        modCount++;
	        E oldValue = elementData(index);
	
	        int numMoved = size - index - 1;
	        if (numMoved > 0)
	            System.arraycopy(elementData, index+1, elementData, index,
	                             numMoved);
	        elementData[--size] = null; // clear to let GC do its work
	
	        return oldValue;
	    }

	ArrayList获取指定下标的元素的方法：
	public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }



	LinkedList的删除元素的方法：
	public boolean remove(Object o) {
	        if (o == null) {
	            for (Node<E> x = first; x != null; x = x.next) {
	                if (x.item == null) {
	                    unlink(x);
	                    return true;
	                }
	            }
	        } else {
	            for (Node<E> x = first; x != null; x = x.next) {
	                if (o.equals(x.item)) {
	                    unlink(x);
	                    return true;
	                }
	            }
	        }
	        return false;
	    }


	LinkedList获取指定下标的方法：
	public E get(int index) {
	        checkElementIndex(index);
	        return node(index).item;
	    }
	
	Node<E> node(int index) {
	        // assert isElementIndex(index);
	
	        if (index < (size >> 1)) {
	            Node<E> x = first;
	            for (int i = 0; i < index; i++)
	                x = x.next;
	            return x;
	        } else {
	            Node<E> x = last;
	            for (int i = size - 1; i > index; i--)
	                x = x.prev;
	            return x;
	        }
	    }


##用法

**LinkedList:**

	boolean add();
	E element();	获取不移除第一个元素
	E get(int);
	E remove();		获取并移除第一个元素
	E remove(index);获取并移除指定下标元素

	boolean offer(E e);		从最后插入元素
	boolean offerFirst(E e);
	boolean offerLast(E e);

	E peek();	获取不移除第一个元素
	E peekFirst();
	E peekLast();

	E poll();	获取并移除第一个元素
	E pollFirst();
	E pollLast();

	E pop();	从头弹出
	void push();从头放入

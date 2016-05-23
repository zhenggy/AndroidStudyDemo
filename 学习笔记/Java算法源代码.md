##Java算法源代码

1.二分搜索
	
	private static <T>
	    int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
	        int low = 0;
	        int high = list.size()-1;
	
	        while (low <= high) {
	            int mid = (low + high) >>> 1;
	            Comparable<? super T> midVal = list.get(mid);
	            int cmp = midVal.compareTo(key);
	
	            if (cmp < 0)
	                low = mid + 1;
	            else if (cmp > 0)
	                high = mid - 1;
	            else
	                return mid; // key found
	        }
	        return -(low + 1);  // key not found
	    }
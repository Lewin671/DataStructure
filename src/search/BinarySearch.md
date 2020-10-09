# BinarySearch

## upper_bound(a,low,high,key)

upper_bound： 返回第一个**大于**key的元素的下标。

实现：
`mid = (high+low) >>> 1;`
可以看到`low <= mid < high`,

- 如果`a[mid] > key`,说明high需要下调，所以`high = mid`
- 如果`a[mid] < key`，说明low需要上调,所以`low = low+1`
- 如果`a[mid] == key`，说明我们还需要尝试将low上调，所以`low = low+1`

由于每一次循环要么high减小，要么low增大,所以循环必然会退出。最后的结果是`low=high`，所以我们返回的结果是low是high都无所谓。

## lower_bound(a,low,high,key)

lower_bound： 返回第一个**大于等于**key的元素的下标。

实现：
`mid = (high+low) >>> 1;`
可以看到`low <= mid < high`,

- 如果`a[mid] > key`,说明high需要下调，所以`high = mid`
- 如果`a[mid] < key`，说明low需要上调,所以`low = low+1`
- 如果`a[mid] == key`，说明我们还需要尝试将high下调，所以`high=mid`

由于每一次循环要么high减小，要么low增大,所以循环必然会退出。最后的结果是`low=high`，所以我们返回的结果是low是high都无所谓。

## binary_search(a,low,high,key)

binary_search: 返回元素的值等于key的下标，如果不存在则返回-1.

实现：
`mid = (high+low) >>> 1;`
可以看到`low <= mid < high`,

- 如果`a[mid] > key`,说明high需要下调，所以`high = mid`
- 如果`a[mid] < key`，说明low需要上调,所以`low = low+1`
- 如果`a[mid] == key`，说明我们已经找到这个下标，返回`mid`即可

如果跳出循环，说明没有找到，则返回-1。

## 总结

从三个二分查找的方法可以看的出来，主要的区别在于如何对待`a[mid]==key`的情况。
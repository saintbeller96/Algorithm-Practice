#BOJ2208 보석줍기 

## Main Idea 누적합
1. 구간 (x, y)의 합
	- sum(x+1, y) (x<=y) = psum(y) - psum(x)

2. y(y>=m)에서 길이가 m이상인 최대 구간합
	- max_sum(y) = psum(y) - min(psum(y-m), psum(y-m-1), psum(y-m-2), ... ,psum(0))
	- max_sum(y+1) = psum(y+1) - min(psum(y+1-m), psum(y-m), ... ,psum(0))
3. 점화식 max_sum(n) = psum(n) - min({psum(k) | y-m >= k >= 0})
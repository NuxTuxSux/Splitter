using Images

img = load(ARGS[1])
h, _ = size(img)
N = parse(Int, ARGS[2])

[save("$i.png",img[i*(h÷N)+1:(i+1)*(h÷N),:]) for i in 0:(N-1)]
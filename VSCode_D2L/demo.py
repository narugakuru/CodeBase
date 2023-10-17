def heapify(arr, n, i):
    largest = i  # 将最大值初始化为根节点
    left = 2 * i + 1
    right = 2 * i + 2

    # 比较左子节点和根节点的值
    if left < n and arr[left] > arr[largest]:
        largest = left

    # 比较右子节点和当前最大值
    if right < n and arr[right] > arr[largest]:
        largest = right

    # 如果最大值不是根节点，则交换它们，并递归调整堆
    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)


def heap_sort(arr):
    n = len(arr)

    # 构建最大堆
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # 逐步取出堆顶元素，放到数组末尾，并重新调整堆
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0)

    return arr


arr = [12, 11, 13, 63, 312, 5, 6, 7]
sorted_arr = heap_sort(arr)
print(sorted_arr)


version: '3'
services:
  gpt_academic_nolocalllms:
    image: ghcr.io/binary-husky/gpt_academic_nolocal:master
    environment:
      API_KEY: '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'
      USE_PROXY: 'True'
      proxies: '{ "http": "socks5h://localhost:10880", "https": "socks5h://localhost:10880" }'
      LLM_MODEL: 'gpt-3.5-turbo'
      AVAIL_LLM_MODELS: '["gpt-3.5-turbo", "api2d-gpt-3.5-turbo", "gpt-4", "api2d-gpt-4"]'
      WEB_PORT: '22303'
      ADD_WAIFU: 'True'
      THEME: 'Chuanhu-Small-and-Beautiful'
      DEFAULT_WORKER_NUM: '10'
      AUTHENTICATION: '    [("root", "noesis")]'
    # 与宿主的网络融合
    network_mode: "host"
    command: >
      bash -c "python3 -u main.py"

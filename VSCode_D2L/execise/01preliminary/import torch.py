import torch

# 计算向量的 L1 范数
vector = torch.tensor([1, -2, 3, -4, 5], dtype=torch.float32)
l1_norm = torch.linalg.norm(vector, ord=1)
print(l1_norm)  # 输出: tensor(15)

# 计算矩阵的 Frobenius 范数
matrix = torch.tensor([[1, 2], [3, 4], [5, 6]], dtype=torch.float32)
frobenius_norm = torch.linalg.norm(matrix)
print(frobenius_norm)  # 输出: tensor(9.5394)

# 沿指定维度计算矩阵的 L2 范数
matrix = torch.tensor([[1, 2], [3, 4], [5, 6]], dtype=torch.float32)
l2_norm_along_dim0 = torch.linalg.norm(matrix, ord=2, dim=0)
print(l2_norm_along_dim0)  # 输出: tensor([5.9161, 7.3485])

# 保持输出张量的维度和输入张量相同
matrix = torch.tensor([[1, 2], [3, 4], [5, 6]], dtype=torch.float32)
norm_with_keepdim = torch.linalg.norm(matrix, ord="fro", keepdim=True)
print(norm_with_keepdim.shape)  # 输出: torch.Size([1, 1])

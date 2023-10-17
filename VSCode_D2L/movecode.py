import nbformat
import os


def copy_code_blocks(input_file, output_file):
    # 读取ipynb文件，指定编码方式为utf-8
    with open(input_file, 'r', encoding='utf-8') as f:
        nb = nbformat.read(f, nbformat.NO_CONVERT)

    # 打开输出文件
    with open(output_file, 'w') as f:
        # 遍历每个单元格
        for cell in nb['cells']:
            # 检查单元格类型是否为代码块
            if cell['cell_type'] == 'code':
                # 将代码块写入输出文件
                for line in cell['source']:
                    f.write(line)
                # 添加三行空行
                f.write('\n' * 3)

    print('代码块已成功复制到文件:', output_file)


# 使用示例
input_file = 'E:\Code\VSCode_D2L\pytorch-d2l\chapter_linear-networks\softmax-regression-scratch.ipynb'  # 输入的ipynb文件名
file_name = os.path.basename(input_file)
output_file = file_name.replace('.ipynb', '.py')  # 输出的文本文件名

copy_code_blocks(input_file, output_file)

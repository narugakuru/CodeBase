import nbformat
import codecs


def export_code_outputs(input_file, output_file):
    # 读取ipynb文件，指定编码方式为utf-8
    with codecs.open(input_file, 'r', encoding='utf-8') as f:
        nb = nbformat.read(f, nbformat.NO_CONVERT)

    # 遍历notebook的所有单元格
    outputs = []
    for cell in nb.cells:
        if cell.get('outputs'):
            # 提取每个单元格的运行结果
            cell_outputs = [
                output for output in cell['outputs'] if output.get('text')]
            outputs.extend(cell_outputs)

    # 将运行结果写入输出文件
    with open(output_file, 'w') as f:
        for output in outputs:
            text = output['text']
            f.write(text)
            f.write('\n')

    print('运行结果已成功复制到文件:', output_file)


# 使用示例
input_file = 'E:/CodeAchieve/CPIPC/breast_cancer/problem1.ipynb'  # 输入的ipynb文件名
output_file = 'output.txt'  # 输出的文本文件名

export_code_outputs(input_file, output_file)

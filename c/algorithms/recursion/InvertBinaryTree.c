/* 反转二叉树
 *
 * 将二叉树的左子树反转成右子树，右子树反转成左子树
 */

#include <stdio.h>
#include <stdlib.h>

struct tnode
{
    int val;
    struct tnode *left;
    struct tnode *right;
};

/**
 * 反转二叉树：时间复杂度 O(n) ，空间复杂度 O(log n)
 *
 * 解题步骤：反转左子树，反转右子树，反转根节点
 */
struct tnode *invertTree(struct tnode *root)
{
    // 叶子结果不能翻转
    if (root == NULL)
        return NULL;

    struct tnode *left = invertTree(root->left); // 反转左子树

    struct tnode *right = invertTree(root->right); // 反转右子树

    // 反转根节点
    root->right = left;
    root->left = right;

    return root;
}

int main(int argc, char const *argv[])
{
    /* 组装二叉树比较复杂，此处不再实现 */

    return 0;
}

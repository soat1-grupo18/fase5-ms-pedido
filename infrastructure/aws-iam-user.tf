// Bad practice. It should be an IAM Role.
resource "aws_iam_user" "ms_pedido" {
  name = "ms-pedido"
  path = "/fiap-store/"
}

resource "aws_iam_access_key" "ms_pedido" {
  user = aws_iam_user.ms_pedido.name
}

data "aws_iam_policy_document" "ms_pedido" {
  statement {
    effect = "Allow"
    actions = [
      "sns:GetTopicAttributes",
      "sns:Publish"
    ]
    resources = [
      aws_sns_topic.ms_pedido.arn
    ]
  }
}

resource "aws_iam_user_policy" "ms_pedido" {
  name   = "application"
  user   = aws_iam_user.ms_pedido.name
  policy = data.aws_iam_policy_document.ms_pedido.json
}

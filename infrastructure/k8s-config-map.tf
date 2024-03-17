resource "kubernetes_config_map" "example" {
  metadata {
    name      = "application-config"
    namespace = "ms-pedido"
  }

  data = {
    aws_region             = data.aws_region.current.name
    aws_sns_endpoint       = "sns.${data.aws_region.current.name}.amazonaws.com"
    aws_sns_ms_pedido_arn  = aws_sns_topic.ms_pedido.arn
    spring_data_source_url = "jdbc:postgresql://${aws_db_instance.fiap_ms_pedido.endpoint}/${aws_db_instance.fiap_ms_pedido.db_name}"
  }

  depends_on = [kubernetes_namespace.ms_pedido]
}

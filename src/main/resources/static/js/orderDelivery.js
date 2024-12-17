// 为每个“发货”按钮添加事件监听器
document.querySelectorAll('.deliveryButton').forEach(function (button) {
    button.addEventListener('click', function () {
        // 获取订单ID
        var itemId = this.closest('tr').querySelector('.selectItem').value;
        // 发起AJAX请求到后端
        fetch('/seller/orderDelivery', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({itemId: itemId})
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 处理响应，例如显示一个消息
                alert('发货成功');
                window.location.reload();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});
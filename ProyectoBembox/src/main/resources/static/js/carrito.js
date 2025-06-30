document.addEventListener("DOMContentLoaded", () => {

    // 🔄 Recalcula los subtotales de cada producto y el total general
    const updateSubtotal = () => {
        let total = 0;

        document.querySelectorAll("tbody tr").forEach((row, index) => {
            const cantidad = parseInt(row.querySelector(".cantidad").value);
            const precio = parseFloat(row.querySelector(".precio-unitario").textContent);
            const subtotal = cantidad * precio;

            // ✅ Actualiza el subtotal individual del producto
            const subtotalElem = row.querySelector(`#subtotal-${index}`);
            if (subtotalElem) {
                subtotalElem.textContent = subtotal.toFixed(2);
            }

            // ✅ Acumula al total general
            total += subtotal;
        });

        // ✅ Muestra el subtotal general
        const totalElem = document.getElementById("subtotal-general");
        if (totalElem) {
            totalElem.textContent = total.toFixed(2);
        }
    };

    // ⬆️⬇️ Escucha los botones "+" y "-" para cambiar la cantidad
    document.querySelectorAll(".product-quantity").forEach(container => {
        const productoId = container.getAttribute("data-producto-id");
        const input = container.querySelector(".cantidad");

        // ➕ Aumentar cantidad
        container.querySelector(".increment").addEventListener("click", () => {
            let cantidad = parseInt(input.value);
            cantidad++;
            input.value = cantidad;

            actualizarCantidad(productoId, cantidad); // 📨 Envía a backend
            updateSubtotal();                         // 🔄 Recalcula totales
        });

        // ➖ Disminuir cantidad
        container.querySelector(".decrement").addEventListener("click", () => {
            let cantidad = parseInt(input.value);
            if (cantidad > 1) {
                cantidad--;
                input.value = cantidad;

                actualizarCantidad(productoId, cantidad); // 📨 Envía a backend
                updateSubtotal();                         // 🔄 Recalcula totales
            }
        });
    });

    // 📨 Llama al backend para actualizar la cantidad en sesión
    function actualizarCantidad(productoId, cantidad) {
        fetch('/carrito/actualizar-cantidad', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `productoId=${productoId}&cantidad=${cantidad}`
        });
    }
});


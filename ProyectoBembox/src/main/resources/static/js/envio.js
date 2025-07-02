function toggleCamposDireccion() {
    const tipoEntrega = document.querySelector('input[name="tipoEntrega"]:checked');
    const camposDireccion = document.getElementById('campos-domicilio');

    if (tipoEntrega) {
        const esDomicilio = tipoEntrega.value === 'domicilio';
        camposDireccion.style.display = esDomicilio ? 'block' : 'none';

        // 🟢 NUEVO: desactiva o activa campos para evitar errores de validación
        const direccionInput = document.querySelector('input[name="direccion"]');
        const distritoSelect = document.querySelector('select[name="distrito"]');

        if (direccionInput) direccionInput.disabled = !esDomicilio;
        if (distritoSelect) distritoSelect.disabled = !esDomicilio;
    }

    actualizarTotal(); // 🟡 IMPORTANTE: también actualiza el total dinámicamente
}

function actualizarTotal() {
    const tipoEntrega = document.querySelector('input[name="tipoEntrega"]:checked');
    const subtotalText = document.getElementById('subtotal').innerText.replace(/[^\d.]/g, '');
    const subtotal = parseFloat(subtotalText) || 0;

    let envio = 0;
    if (tipoEntrega && tipoEntrega.value === 'domicilio') {
        envio = 10.00; // 🧾 Costo fijo de envío si se elige envío a domicilio
    }

    const total = subtotal + envio;

    document.getElementById('costoEnvio').innerText = 'S/ ' + envio.toFixed(2);
    document.getElementById('total').innerText = 'S/ ' + total.toFixed(2);
}

window.addEventListener('DOMContentLoaded', function () {
    // ✅ Inicializa todo al cargar la página
    toggleCamposDireccion();

    // 🔁 Registra eventos cuando el usuario cambia el tipo de entrega
    const radios = document.querySelectorAll('input[name="tipoEntrega"]');
    radios.forEach(radio => {
        radio.addEventListener('change', toggleCamposDireccion);
    });
});

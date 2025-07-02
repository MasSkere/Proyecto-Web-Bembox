function seleccionarMetodo(metodo) {
    document.querySelectorAll('.metodo-pago').forEach(card => card.classList.remove('selected'));
    document.getElementById('form-tarjeta').style.display = 'none';
    document.getElementById('form-yape').style.display = 'none';

    document.getElementById(metodo).checked = true;
    const selectedCard = document.getElementById(metodo).closest('.metodo-pago');
    selectedCard.classList.add('selected');

    if (metodo === 'tarjeta') {
        document.getElementById('form-tarjeta').style.display = 'block';
    }
    if (metodo === 'yape') {
        document.getElementById('form-yape').style.display = 'block';
    }
}

function confirmarPago() {
    const modal = new bootstrap.Modal(document.getElementById('modalExito'));
    modal.show();

    setTimeout(() => {
        document.querySelector('form').submit();
    }, 3000);
}

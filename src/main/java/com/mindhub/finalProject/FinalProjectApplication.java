package com.mindhub.finalProject;

import com.mindhub.finalProject.Services.*;
import com.mindhub.finalProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(UserService userService, PetService petService, PurchaseService purchaseService, CardService cardService,
									  CategoryService categoryService, HospitalizationService hospitalizationService, MedicalHistoryService medicalHistoryService,
									  PrepaidService prepaidService, ProductService productService, ShiftService shiftService){
		return(args) -> {

			/* CLIENTES */
			Client client = new Client(42965010, "pedro@gmail.com",
					passwordEncoder.encode("123"), Authority.CLIENT, "Pedro", "Bueno",
					"Av. Los Pozos 4112", "CABA", "Buenos Aires",
					LocalDate.now().withYear(2000).withMonth(8).withDayOfMonth(26), 5200);
			userService.saveClient(client);


			/* ADMIN*/
			Admin admin = new Admin(12345678,"admin@admin",passwordEncoder.encode("123"),Authority.ADMIN);
			userService.saveAdmin(admin);


			/* MASCOTAS */
			Pet pet = new Pet("Roko", "Bulldog", 20,
					15, PetType.PERRO, client);
			petService.savePet(pet);

			Pet pet2 = new Pet("Luna", "Angora", 2,
					6, PetType.GATO, client);
			petService.savePet(pet2);

			/*VETERINARIOS*/
			Veterinary veterinary1 = new Veterinary(35345689,"lucas.rodriguez@gmail.com",passwordEncoder.encode("#lucas123"),Authority.VETERINARY,"Lucas", "Rodriguez","15-4897-5632",VeterinaryCategory.CARDIOLOGO,Arrays.asList("12:00", "13:40", "15:00"));
			userService.saveVeterinary(veterinary1);

			Shift shift = new Shift(client, veterinary1, LocalDateTime.now(), "Pedro Bueno");
			shiftService.saveShift(shift);


			Veterinary veterinary2 = new Veterinary(75765421,"jeremias.barrios@gmail.com",passwordEncoder.encode("Jere123"),Authority.VETERINARY,"Jeramias", "Barrios","95-6213-5478",VeterinaryCategory.CIRUJANO, Arrays.asList("08:00", "10:20", "17:00"));
			userService.saveVeterinary(veterinary2);

			Veterinary veterinary3 = new Veterinary(98765432,"facundo.navarro@gmail.com.com",passwordEncoder.encode("Facu123"),Authority.VETERINARY,"Facundo", "Navarro","34-6445-3209",VeterinaryCategory.OFTALMOLOGO, Arrays.asList("07:00", "9:20", "13:00"));
			userService.saveVeterinary(veterinary3);

			Veterinary veterinary4 = new Veterinary(87654321,"santi.brito@gmail.com",passwordEncoder.encode("santi123"),Authority.VETERINARY,"Santiago", "Brito","76-1474-9465",VeterinaryCategory.CLINICO, Arrays.asList("10:00", "11:20", "16:00"));
			userService.saveVeterinary(veterinary4);

			/* COMPRAS */
			Purchase purchase1 = new Purchase(client, 200, LocalDateTime.now().withNano(0));
			purchaseService.savePurchase(purchase1);
			Purchase purchase2 = new Purchase(client, 350, LocalDateTime.now().withNano(0));
			purchaseService.savePurchase(purchase2);
			Purchase purchase3 = new Purchase(client, 100, LocalDateTime.now().withNano(0));
			purchaseService.savePurchase(purchase3);
			Purchase purchase4 = new Purchase(client, 50, LocalDateTime.now().withNano(0));
			purchaseService.savePurchase(purchase4);
			Purchase purchase5 = new Purchase(client, 80, LocalDateTime.now().withNano(0));
			purchaseService.savePurchase(purchase5);

			/* TARJETAS */
			Card card1 = new Card(client,
					client.getName() + " "+ client.getLastName(),
					"4220-5006-5012-7831", 233, LocalDate.now().plusYears(5));
			cardService.saveCard(card1);

			Card card2 = new Card(client,
					client.getName() +" "+ client.getLastName(),
					"6504-8652-1565-6823", 731, LocalDate.now().plusYears(5));
			cardService.saveCard(card2);

			Card card3 = new Card(client,
					client.getName() +" "+ client.getLastName(),
					"1912-5452-4510-6503", 233, LocalDate.now().plusYears(5));
			cardService.saveCard(card3);

			/* CATEGORIAS */
			Category categoryA= new Category(100, 0.1, "Plan Basico");
			categoryService.saveCategory(categoryA);
			Category categoryB= new Category(150, 0.15, "Plan Extra");
			categoryService.saveCategory(categoryB);
			Category categoryC= new Category(200, 0.2, "Plan Extra Plus");
			categoryService.saveCategory(categoryC);

			/* OBRAS SOCIALES */
			Prepaid prepaid1 = new Prepaid(client, categoryB);
			prepaidService.savePrepaid(prepaid1);

			/* HISTORIA CLINICA */
			MedicalHistory medicalHistory1 =
					new MedicalHistory(pet, pet.getName(), pet.getRace(),
							pet.getWeight(), pet.getPetType(), "Sin observaciones.");
			medicalHistoryService.saveMedicalHistory(medicalHistory1);

			MedicalHistory medicalHistory2 =
					new MedicalHistory(pet2, pet2.getName(), pet2.getRace(),
							pet2.getWeight(), pet2.getPetType(), "Con patologias previas.");
			medicalHistoryService.saveMedicalHistory(medicalHistory2);

			Hospitalization hospitalization1= new Hospitalization(medicalHistory1,LocalDateTime.now(),"Operacion de ojos");
			hospitalizationService.saveHospitalization(hospitalization1);

			Hospitalization hospitalization2=new Hospitalization(medicalHistory1,LocalDateTime.now(),"Operacion de Molares");
			hospitalizationService.saveHospitalization(hospitalization2);

			Hospitalization hospitalization3=new Hospitalization(medicalHistory2,LocalDateTime.now(),"Castracion");
			hospitalizationService.saveHospitalization(hospitalization3);



			/* Turnos */
			Shift turno1 = new Shift(client,veterinary1,LocalDateTime.now().plusDays(5).withHour(8).withMinute(0).withSecond(0).withNano(0), "Roko");
			shiftService.saveShift(turno1);

			Shift turno2 = new Shift(client, veterinary1, LocalDateTime.now().plusDays(3).withHour(10).withMinute(0).withSecond(0).withNano(0), "Luna");
			shiftService.saveShift(turno2);

			Shift turno3 = new Shift(client, veterinary1, LocalDateTime.now().plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0), "Jorge Bueno");
			shiftService.saveShift(turno3);

			Shift turno4 = new Shift(pet,LocalDateTime.now().plusDays(3),veterinary1, "Naylu Vivas");
			shiftService.saveShift(turno4);

			Shift turno5 = new Shift(pet,LocalDateTime.now().plusDays(4),veterinary1, "Grana");
			shiftService.saveShift(turno5);

			/* Vacunas para mascotas */
			Vaccine vaccinePet1= new Vaccine("Vacuna para la prevención del Tétano.",pet,"2ml/Kg");
			productService.saveVaccine(vaccinePet1);
			Vaccine vaccinePet2= new Vaccine("Vacuna para la prevención de la Parvovirosis canina.",pet,"3ml/Kg");
			productService.saveVaccine(vaccinePet2);
			Vaccine vaccinePet3= new Vaccine("Vacuna para la prevención de la Rabia en caninos y felinos.",pet,"4ml/Kg");
			productService.saveVaccine(vaccinePet3);

			/* Vacunas */
			Product vaccine = new Product("Vacuna para la prevención del Tétano.","Rosenbusch","Vacuna indicada para la prevención del Tétano en los equinos, ovinos, caprinos, y caninos.","https://gcdnb.pbrd.co/images/eWsMF1PvhNVE.jpg?o=1",5,0,5870,ProductCategory.VACCINE);
			productService.saveProduct(vaccine);

			Product vaccine1 = new Product("Vacuna para la prevención de la Parvovirosis canina.","Rosenbusch","Vacuna indicada para la prevención de la Parvovirosis canina.","https://gcdnb.pbrd.co/images/jDKIClb3ti3Q.jpg?o=1",10,0,5970,ProductCategory.VACCINE);
			productService.saveProduct(vaccine1);

			Product vaccine2 = new Product("Vacuna para la prevención de la Coronavirosis en caninos.","Rosenbusch","Vacuna indicada para la prevención de la Coronavirosis en caninos.","https://gcdnb.pbrd.co/images/7g2oFEB7WyKq.jpg?o=1",8,0,6700,ProductCategory.VACCINE);
			productService.saveProduct(vaccine2);

			Product vaccine3 = new Product("Vacuna para la prevención de la Rabia en caninos y felinos.","Rosenbusch","Vacuna indicada para la prevención de la Rabia en caninos y felinos.","https://gcdnb.pbrd.co/images/z6fTSrIfs7xY.jpg?o=1",2,0,6780,ProductCategory.VACCINE);
			productService.saveProduct(vaccine3);

			Product vaccine4 = new Product("Vacuna para la prevención del Moquillo","Rosenbusch","Vacuna indicada para la prevención del Moquillo, Hepatitis infecciosa, Enfermedad respiratoria por adenovirus y Parainfluenza canina.","https://gcdnb.pbrd.co/images/Ijv37fX524mS.jpg?o=1",15,0,8780,ProductCategory.VACCINE);
			productService.saveProduct(vaccine4);

			Product vaccine5 = new Product("Vacuna para la prevención de la Rinotraqueítis viral ","Rosenbusch","Vacuna Virus inactivados con B.E.I de la Rinotraqueítis felina (Herpes virus felino I)","https://gcdnb.pbrd.co/images/IPEcApWKPr2y.jpg?o=1",17,0,1780,ProductCategory.VACCINE);
			productService.saveProduct(vaccine5);




			/* Medicamentos */
			Product mediccine = new Product("Dentalplax 250 Ml Perros Y Gatos","John Martin","Anticeptico bucal para la higiene de caninos y felinos. 250 Ml.","https://http2.mlstatic.com/D_NQ_NP_627326-MLA44938504640_022021-O.webp",11,0,1403,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine);

			Product mediccine1 = new Product("Previcox 227 Mg X 60 Comp","John Martin","Previcox comprimidos masticables es un anti-inflamatorio no esteroideo para el alivio del dolor y la inflamación asociados a la osteoartritis en el perro.","https://nanolog.vteximg.com.br/arquivos/ids/158794-1000-1000/0720-0073.jpg?v=636645816570200000",2,0,14660,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine1);

			Product mediccine2 = new Product("Hifarmax Omnicutis Atopicalm","John Martin","Omnicutis ATOPICALM Alimento complementario dietético de apoyo a la función dérmica ","https://static.miscota.com/media/1/photos/products/173502/omnicutis-atopicalm-30-capsulas_1_g.jpeg",5,0,1936,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine2);

			Product mediccine3 = new Product("Total full cg X 15ml","John Martin","Antiparasitario interno en suspensión oral.","https://tienda.faunatikos.com.ar/img/articulos/12830.jpg",10,0,985,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine3);

			Product mediccine4 = new Product("Singestar Comp. X 8","John Martin","Progestageno anticonceptivo, para la prevencion o anulacion del celo, tratamiento de tumores estrogeno. En perros: para el tratamiento de la hipersexualidad.","https://nanolog.vteximg.com.br/arquivos/ids/162364-1000-1000/singestar-comprimidos-x-8--1-.jpg?v=637215244796500000",8,0,990,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine4);

			Product mediccine5 = new Product("Fatro Sac Vetramil Spray 100 ml","John Martin","La miel aporta una alta concentración de enzimas con efecto antibacteriano Favorece una rápida cicatrización de las heridas. Los excipientes suavizan la piel y la protegen de la deshidratación.","https://static.carethy.net/media/4/photos/products/493828/vetramil-spray-100-ml_1_g.png",6,0, 2824,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine5);

			Product mediccine6 = new Product("Aquadent Enjuague Bucal Perros Y Gatos X 250 Ml","John Martin","Aquadent Enjuague Bucal Perros Y Gatos X 250 Ml","https://http2.mlstatic.com/D_NQ_NP_866527-MLA49432834552_032022-O.webp",9,0,3250,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine6);

			Product mediccine7 = new Product("Stangest Cicavet Cicatrizante Spray","John Martin","Cicavet simula el proceso natural de cicatrización de las heridas sin costras.","https://static.miscota.com/media/1/photos/products/113663/113663_1_g.jpg",5,0,1525,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine7);

			Product mediccine8 = new Product("Tonico Total X 200 Cc.","John Martin","Complementa y previene estados carenciales en animales con dietas deficientes o en animales que padecen estados debilitantes o carenciales de cualquier otro origen. Favorece además el desarrollo de un pelaje más fino y lustroso.","https://nanolog.vteximg.com.br/arquivos/ids/158718-1000-1000/0780-0027.jpg?v=636645816161270000",5,0,1430,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine8);

			Product mediccine9 = new Product("Adaptil Transport Spray Feromonas Perros 60ml-petit Pet Shop","Farmadiet","Ofrece una facil y comoda solucion para ayudar a que tu perro este mas tranquilo durante los viajes o en las visitas al veterinario. Simplemente pulveriza el spray directamente en el transportin 15 minutos antes de introducir al perro","https://http2.mlstatic.com/D_NQ_NP_639055-MLA48160221395_112021-O.webp",6,0,4800,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine9);

			Product mediccine10 = new Product("Artrin Plus X 18 Comp.","Farmadiet","Regenerador del cartílago indicado para el tratamiento de las enfermedades articulares degenerativas graves artrosis, artritis, osteocondroartrosis, espondilosis, espondiloartrosis anquilosante y procesos degenerativos articulares y tendinosos.","https://nanolog.vteximg.com.br/arquivos/ids/158780-1000-1000/6000-0106.jpg?v=636645816482400000",2,0,3600,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine10);

			Product mediccine11 = new Product("VetPlus Colaid para Problemas Gastrointestinales en Perros","Farmadiet","Colaid contiene una combinación de ingredientes destinados a mantener la salud intestinal. Puede utilizarse como apoyo para la salud gastrointestinal a largo plazo en perros.","https://static.miscota.com/media/1/photos/products/048696/colaid-para-problemas-gastrointestinales-en-perros_1_g.jpeg",4,0,12359,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine11);

			Product mediccine12 = new Product("Toltrazol X 3 COMP DE 200mg","Farmadiet","Antiparacitarios Fórmula: TOLTRAZURIL 200,00 MG ,EXCIPIENTES C.S.P. 750,00 MG","https://tienda.faunatikos.com.ar/img/articulos/3665.jpg",9,0,1237,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine12);

			Product mediccine13 = new Product("Farmadiet Complemento Irc-Vet (Perros/Gatos)","Farmadiet","Fórmula a base de nutrientes que ayudan al correcto funcionamiento de las células renales. La incapacidad de los riñones de funcionar correctamente, se debe en gran medida a una pérdida de tejido renal funcional prolongada.","https://static.miscota.com/media/1/photos/products/043772/43772-ircvet-3d-caja-comprimidos-con-sombra-1-600x600_0_g.png",3,0,4259,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine13);

			Product mediccine14 = new Product("Artroglycan X 90 Comp.","Farmadiet","Antiartrósico, antiartrítico, condroprotector y regenerador del cartílago articular. Acción antiiflamatoria natural.","https://nanolog.vteximg.com.br/arquivos/ids/165305-1000-1000/13.png?v=637425232857330000",8,0,6580,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine14);

			Product mediccine15 = new Product("Nexgard Spectra Para Perros - 2 a 3,5 Kg","Ecthol","Nexgard Spectra Para Perros - 2 a 3,5 Kg","https://puppis.vteximg.com.br/arquivos/ids/170186-1000-1000/337013.png?v=637142658788130000",5,0,1760,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine15);

			Product mediccine16 = new Product("Antipulgas Y Garrapatas Bravecto Para Perros - 2 a 4,5 Kg","Ecthol","Antipulgas Y Garrapatas Bravecto Para Perros - 2 a 4,5 Kg","https://puppis.vteximg.com.br/arquivos/ids/167383-1000-1000/366255.jpg?v=637014017122630000",1,0,4680,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine16);

			Product mediccine17 = new Product("Nexgard Para Perros Extra Grandes En Comprimidos - 25-50kg","Ecthol","Nexgard Para Perros Extra Grandes En Comprimidos - 25-50kg","https://puppis.vteximg.com.br/arquivos/ids/170185-1000-1000/300057.png?v=637142654515000000",6,0,2770,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine17);

			Product mediccine18 = new Product("Nexgard Para Perros Grandes En Comprimidos - 10-25kg","Ecthol","Nexgard Para Perros Grandes En Comprimidos - 10-25kg","https://puppis.vteximg.com.br/arquivos/ids/170184-1000-1000/300055.png?v=637142653974100000",15,0,2320,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine18);

			Product mediccine19 = new Product("Power Ultra ( 1 Pipeta ) - 2-4Kg","Ecthol","Power Ultra ( 1 Pipeta ) - 2-4Kg","https://puppis.vteximg.com.br/arquivos/ids/157833-1000-1000/Power-Ultra.jpg?v=635852684254630000",3,0,610,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine19);

			Product mediccine20 = new Product("Pipeta Ecthol GMP Antipulgas Y Garrapas Para Perro - Hasta 5 Kg","Ecthol","Pipeta Ecthol GMP Antipulgas Y Garrapas Para Perro - Hasta 5 Kg","https://puppis.vteximg.com.br/arquivos/ids/167390-1000-1000/382055.jpg?v=637014017151270000",5,0,350,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine20);

			Product mediccine21 = new Product("Antiparasitario Labyes Canis Full Spot - 5-10kg","Labyes","Antiparasitario Labyes Canis Full Spot - 5-10kg","https://puppis.vteximg.com.br/arquivos/ids/174691-1000-1000/361076.jpg?v=637452603409500000",6,0,1750,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine21);

			Product mediccine22 = new Product("Antipulgas Y Garrapatas Credelio 3 Comprimidos - 2,5 a 5,5 Kg","Credelio","Antipulgas Y Garrapatas Credelio 3 Comprimidos - 2,5 a 5,5 Kg","https://puppis.vteximg.com.br/arquivos/ids/171800-1000-1000/336065.jpg?v=637304603152400000",2,0,3210,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine22);

			Product mediccine23 = new Product("Antiparasitario Externo Ecthol Para Perro Y Ambiente - 100Cc","Ecthol","Antiparasitario Externo Ecthol Para Perro Y Ambiente - 100Cc","https://puppis.vteximg.com.br/arquivos/ids/158377-1000-1000/382048.jpg?v=635993660628100000",7,0,1140,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine23);

			Product mediccine24 = new Product("Vitamínico Kualcos Kualcovit B Jarabe - 100ml","Kualcos","Vitamínico Kualcos Kualcovit B Jarabe - 100ml","https://puppis.vteximg.com.br/arquivos/ids/174695-1000-1000/366063.jpg?v=637452603420170000",12,0,780,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine24);

			Product mediccine25 = new Product("Curabichera Bactrovet Plata AM Pet - 250cc","Laboratorios Konig","Curabichera Bactrovet Plata AM Pet - 250cc","https://puppis.vteximg.com.br/arquivos/ids/174672-1000-1000/338018.jpg?v=637452603356800000",6,0,1020,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine25);

			Product mediccine26 = new Product("Tónico Vitamínico Total Dogs - 80 cc","John Martin","Tónico Vitamínico Total Dogs - 80 cc","https://puppis.vteximg.com.br/arquivos/ids/163262-1000-1000/totaldogs.jpg?v=636657805932000000",5,0,905,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine26);

			Product mediccine27 = new Product("Colotrin Pasta Palatable +HA Para Perro - 200 Gr.","John Martin","Colotrin Pasta Palatable +HA Para Perro - 200 Gr.","https://puppis.vteximg.com.br/arquivos/ids/166311-1000-1000/371073.png?v=636893821006900000",7,0,2500,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine27);

			Product mediccine28 = new Product("Leche Maternizada Completa Maternil Fort Para Perros Y Gatos - 250 Gr","Holliday Scott","Leche Maternizada Completa Maternil Fort Para Perros Y Gatos - 250 Gr","https://puppis.vteximg.com.br/arquivos/ids/188507-1000-1000/366105.jpg?v=637944400098770000",25,0,295,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine28);

			Product mediccine29 = new Product("OHM","Holliday Scott","Biomodulador de la ansiedad de administración oral en comprimidos palatables.","https://nanolog.vteximg.com.br/arquivos/ids/163006-1000-1000/ohm-x-21-comp.jpg?v=637256738287530000",5,0,960,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine29);

			Product mediccine30 = new Product("Condrovet Taste Ha X 100 Comp.","Vetanco","Protege articulaciones, ayudando a frenar su desgaste en perros con sobrepeso, edad avanzada, elevado ejercicio fisico, cachorros de razas grandes en periodo de crecimiento.","https://nanolog.vteximg.com.br/arquivos/ids/165773-1000-1000/CONDROVET-CAJA.jpg?v=637588550505000000",2,0,18600,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine30);

			Product mediccine31 = new Product("Apoquel 5,4 Mg. X 20 Comp.","Zoetis","Apoquel 5,4 mg está indicado para perros desde los 4,5 kg hasta los 27 kg, para el tratamiento del picor asociados a la dermatitis atópica en perros y la dermatitis alérgica canina.","https://nanolog.vteximg.com.br/arquivos/ids/164109-1000-1000/apoquel-54.jpg?v=637309360129470000",4,0,3820,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine31);

			Product mediccine32 = new Product("Juvenia 30 Sobres X 5 Grs.","GatoLac","Complemento dietario para perros con Resveratrol, un antihiperoxidante natural. Combate el envejecimiento de tu mascota y previene enfermedades.","https://nanolog.vteximg.com.br/arquivos/ids/164787-1000-1000/juveniaDeluxbasefoto.jpg?v=637353433727130000",9,0,3650,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine32);

			Product mediccine33 = new Product("Cardiovier E 10 Kg X 20 Comp.","Laboratorios J'anvier","CARDIOVIER E se indica para el tratamiento de la Insuficiencia Cardíaca Congestiva, causada por insuficiencia valvular (endocarditis, endocardiosis mitral) o cardiomiopatía.","https://nanolog.vteximg.com.br/arquivos/ids/164618-1000-1000/cardiovier-10.jpg?v=637335309705130000",12,0,1900,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine33);

			Product mediccine34 = new Product("Dynamide X 30 Comp","Brouwer","Gerontológico. Estimulante y restaurador del dinamismo y la vitalidad, antioxidante, protector del sistema articular. Para caninos y felinos. En comprimidos palatables.","https://nanolog.vteximg.com.br/arquivos/ids/158976-1000-1000/6000-0138.jpg?v=636645817660200000",9,0,2740,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine34);

			Product mediccine35 = new Product("Revolution 12%-1,00Ml. 120Mg. 10,1-20Kg.","Bayer","Parasiticida de amplio espectro, que permite el control y tratamiento de lombrices intestinales, Dirofilarias, Ácaros de la sarna, pulgas adultas, larvas y huevos en perros y gatos","https://nanolog.vteximg.com.br/arquivos/ids/163749-1000-1000/revolution-10-20-kg.jpg?v=637290462842700000",7,0,2150,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine35);

			Product mediccine36 = new Product("Baytril Saborizado 150 Mg X 10 Comp.","Bayer","Antibacteriano enrofloxacina 150,00 mg. ,excipientes c.s.p. 1,00 comprimido administracion oral. perros para la terapia de enfermedades infecciosas causadas por bacterias gram positivas","https://nanolog.vteximg.com.br/arquivos/ids/164635-1000-1000/Baytril-150-mg-pastilla-enrofloxacina-bayer-precio-peru.jpg?v=637336087565200000",2,0,1530,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine36);

			Product mediccine37 = new Product("Antibacteriano C/ Est.10 Ml.","Laboratorio Love Sudamericana","Antibacteriano con esteroides utilizado en procesos infecciosos a germenes sensibles de accion rapida y potente 2 gotas cada 6 horas o criterio del medico veterinario. frasco drop trainner con 10 ml","https://nanolog.vteximg.com.br/arquivos/ids/158970-1000-1000/0760-0022.jpg?v=636645817582770000",4,0,1270,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine37);

			Product mediccine38 = new Product("Novotioc 15 X 20 Grageas","John Martin","Coadyuvante en disfunciones hepaticas. prevencion y tratamiento de es-tados de fatiga. hepatitis. esteatosis hepatica. intoxicaciones por metales pesados.","https://nanolog.vteximg.com.br/arquivos/ids/164630-1000-1000/Pack-NOVOTIOC-comp.jpg?v=637335368761200000",10,0,760,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine38);

			Product mediccine39 = new Product("Felilax X 40 Grs.","John Martin","Laxante suave para perros y gatos para la remocion y eliminacion de los bolos fecales en perros y gatos.como lubricante intestinal en caso de ingestion de cuerpos extraños. constipacion aguda y cronica.","https://nanolog.vteximg.com.br/arquivos/ids/164943-1000-1000/felilaxx.jpg?v=637365534692070000",3,0,940,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine39);

			Product mediccine40 = new Product("Proteliv 15 Ml","Holliday Scott","hepatopatias agudas y cronicas - colecistitis - colangitis - hepatoprotector. perros y gatos: 10 -20 gotas cada 12 horas. duracion a criterio del medico veterinario. frasco-gotero con 15 ml","https://nanolog.vteximg.com.br/arquivos/ids/164621-1000-1000/proteliv.jpg?v=637335312699400000",6,0,930,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine40);

			Product mediccine41 = new Product("Comfortis Perros 18.1 A 27.2 Kg","Brouwer","Comfortis® es un comprimido masticable saborizado que elimina y previene infestaciones por pulgas en perros y gatos por un mes completo","https://nanolog.vteximg.com.br/arquivos/ids/159976-1000-1000/3827-5033.jpg?v=636695108259470000",5,0,1610,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine41);

			Product mediccine42 = new Product("Neo Vitapel X 30 Comp.","Brouwer","Suplemento mineral vitaminico con aminoacidos","https://nanolog.vteximg.com.br/arquivos/ids/160312-1000-1000/0820-0010.jpg?v=636705351336600000",7,0,1800,ProductCategory.MEDICINE);
			productService.saveProduct(mediccine42);



			/* Alimento */
			Product balanced_meal = new Product("Alimento Balanced Perro Adulto Raza Mediana 1.5kg","Balanced","Alimento para Perro Adulto raza Mediana marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Adult_Medium_x3_959b3cb8-68ca-4101-8675-997f7fa9bc28_500x.png?v=1660597422", 42, 0, 2270,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal);

			Product balanced_meal1 = new Product("Alimento Balanced Perro Senior Raza Mediana 1kg","Balanced","Alimento para Perro Senior raza Mediana marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Senior_Medium_x3_fca25259-d4e9-424a-b86c-4292648efcde_500x.png?v=1660597386", 36, 0, 2330,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal1);

			Product balanced_meal2 = new Product("Alimento Balanced Perro Adulto Raza Pequeña 3kg","Balanced","Alimento para Perro Adulto raza Pequeña marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Adult_Small_x3_cfd19a28-b4da-4134-928f-7a8b4817052e_500x.png?v=1660597419", 41, 0, 2210,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal2);

			Product balanced_meal3 = new Product("Alimento Balanced Perro Adulto Raza Grande 3kg","Balanced","Alimento para Perro Adulto raza Grande marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Adult_Large_x3_1809bc36-6d5c-4a32-a068-341761adcd6d_500x.png?v=1660597425", 15, 0, 2210,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal3);

			Product balanced_meal4 = new Product("Alimento Balanced Perro Senior Raza Pequeña 1.5kg","Balanced","Alimento para Perro Senior raza Pequeña marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Senior_Small_x3_b44dbede-ce8e-440c-a7a0-5495dfad563a_500x.png?v=1660597383", 23, 0, 2360,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal4);

			Product balanced_meal5 = new Product("Alimento Balanced Perro Cachorro Raza Mediana 1kg","Balanced","Alimento para Perro Cachorro raza Mediana marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Puppy_Medium_x1_40d7f4af-dbd2-4d35-af8e-d236316c16c8_500x.png?v=1660597412", 11, 0, 920,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal5);

			Product balanced_meal6 = new Product("Alimento Balanced Perro Control de Peso 3kg","Balanced","Alimento para Perro Control de Peso marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/CONTROL_DE_PESO_frente_x3_7efe2876-80b9-40a7-b94f-58e6f6dcea64_500x.png?v=1660597405", 5, 0, 2499,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal6);

			Product balanced_meal7 = new Product("Alimento Balanced Perro Piel Sensible Cerdo y Arroz 1kg","Balanced","Alimento para Perro con Piel Sensible / Alergias marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/balanced-perro-adulto-cerdo-y-arroz-01_7d6c8f13-861b-4592-93a7-a88a0f7975e7_500x.png?v=1660597391", 35, 0, 2399,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal7);

			Product balanced_meal8 = new Product("Alimento Balanced Perro Cachorro Raza Grande 1kg","Balanced","Alimento para Perro Cachorro raza Grande marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/balanced-perro-adulto-cerdo-y-arroz-01_7d6c8f13-861b-4592-93a7-a88a0f7975e7_500x.png?v=1660597391", 8, 0, 899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal8);

			Product balanced_meal41 = new Product("Alimento Balanced Perro Cachorro Raza Grande 3kg","Balanced","Alimento para Perro Cachorro raza Grande marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/balanced-perro-adulto-cerdo-y-arroz-01_7d6c8f13-861b-4592-93a7-a88a0f7975e7_500x.png?v=1660597391", 16, 0, 2199,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal41);

			Product balanced_meal9 = new Product("Alimento Balanced Perro Cachorro Raza Grande 3kg","Balanced","Alimento para Perro Cachorro raza Grande marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Puppy_Large_x1_b6ef17b2-7ea1-4f72-827a-5c983fa2750d_500x.png?v=1660597416", 13, 0, 899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal9);

			Product balanced_meal10 = new Product("Alimento Balanced Perro Cachorro Raza Pequeña 1kg","Balanced","Alimento para Perro Cachorro raza Pequeña marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Puppy_Small_x1_3a1537d4-095a-42d0-a44f-975f3105f2cb_500x.png?v=1660597408", 14, 0, 899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal10);

			Product balanced_meal11 = new Product("Alimento Balanced Perro Senior Raza Grande 1.5kg","Balanced","Alimento para Perro Senior raza Grande marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Senior_Large_x3_9357db72-ed40-42a6-b228-afbd9c671868_500x.png?v=1660597388", 26, 0, 2260,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal11);

			Product balanced_meal12 = new Product("Alimento Balanced Perro Adulto Raza Gigante 20kg","Balanced","Alimento para Perro Adulto raza Gigante marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Giantx20_500x.png?v=1660597428", 3, 0, 10899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal12);

			Product balanced_meal13 = new Product("Alimento Royal Canin Perro Mini Adult 1kg","Royal Canin","Alimento marca Royal Canin para perros adultos de talla pequeña (peso adulto hasta 10kg). De 10 meses a 8 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-mini-adult_360x.png?v=1658170355", 18, 0, 1622,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal13);

			Product balanced_meal14 = new Product("Alimento Royal Canin Perro Mini Puppy 1kg","Royal Canin","Alimento marca Royal Canin para perros cachorros de talla pequeña (peso adulto menor a 10kg). Desde los 2 hasta los 10 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-mini-puppy_360x.png?v=1658170342", 12, 0, 1820,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal14);

			Product balanced_meal15 = new Product("Alimento Royal Canin Perro Medium Puppy 3kg","Royal Canin","Alimento balanceado completo marca Royal Canin para caninos cachorros de talla mediana (peso adulto entre 11 y 25 kg). De 2 a 12 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-medium-puppy_500x.png?v=1658170359", 35, 0, 4773,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal15);

			Product balanced_meal16 = new Product("Alimento Royal Canin Perro Medium Adult 15kg","Royal Canin","Alimento marca Royal Canin completo para perros adultos de talla mediana (de 11 a 25 kg). Desde los 12 meses hasta los 7 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-medium-adult_360x.png?v=1658170365", 4, 0, 14507,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal16);

			Product balanced_meal17 = new Product("Alimento Royal Canin Perro Mini Weight Care 1kg","Royal Canin","Alimento marca Royal Canin para perros adultos de tamaño pequeño (peso adulto entre 1 y 10kg) con tendencia al sobrepeso y/o bajo nivel de actividad. Desde los 10 meses.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/royal-canin-mini-weight-care-canine-care-nutrition-seco_360x.jpg?v=1658170341", 16, 0, 1848,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal17);

			Product balanced_meal18 = new Product("Alimento Royal Canin Perro Maxi Adult 3kg","Royal Canin","Alimento marca Royal Canin completo para perros adultos de talla grande (de 26 a 44 kg). Desde los 15 meses hasta los 5 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-maxi-adult_360x.png?v=1658170379", 23, 0, 4192,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal18);

			Product balanced_meal19 = new Product("Alimento Royal Canin Perro Mini Ageing edad 12+ 1kg","Royal Canin","Alimento marca Royal Canin para perros maduros de talla pequeña (peso adulto hasta 10kg). A partir de los 12 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-mini-ageing_360x.png?v=1658170352", 18, 0, 1732,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal19);

			Product balanced_meal20 = new Product("Alimento Royal Canin Perro Caniche Adulto 1kg","Royal Canin","Alimento marca Royal Canin para perros adultos de raza Caniche. A partir de los 10 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-caniche-adulto-01_500x.png?v=1658170463", 10, 0, 1947,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal20);

			Product balanced_meal21 = new Product("Alimento Royal Canin Perro Caniche Adulto 1kg","Royal Canin","Alimento marca Royal Canin para perros adultos de raza Caniche. A partir de los 10 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-caniche-adulto-01_500x.png?v=1658170463", 10, 0, 1947,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal21);

			Product balanced_meal22 = new Product("Alimento Royal Canin Perro Mini Adult edad 8+ 1kg","Royal Canin","Alimento marca Royal Canin para perros maduros de talla pequeña (peso adulto hasta 10kg). De 8 a 12 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-mini-adult-8_360x.png?v=1658170354", 7, 0, 1732,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal22);

			Product balanced_meal23 = new Product("Alimento Royal Canin Perro Bulldog Francés Cachorro 3kg", "Royal Canin", "Alimento marca Royal Canin para perros cachorros de raza Bulldog Francés. De 2 a 12 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-bulldog-frances-junior-cachorro-01_500x.png?v=1658170468", 27, 0, 5879,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal23);

			Product balanced_meal24 = new Product("Alimento Royal Canin Perro Maxi Puppy 3kg", "Royal Canin", "Alimento marca Royal Canin balanceado completo para caninos cachorros de talla grande (peso adulto entre 26 y 44 kg). De 2 a 15 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-maxi-puppy_500x.png?v=1658170370", 14, 0, 4750,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal24);

			Product balanced_meal25 = new Product("Alimento Royal Canin Perro Bulldog Francés Adulto 3kg", "Royal Canin", "Alimento marca Royal Canin para perros de raza Bulldog Francés. A partir de los 12 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-bulldog-frances-adulto-01_500x.png?v=1658170470", 38, 0, 5296,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal25);

			Product balanced_meal26 = new Product("Alimento Royal Canin Perro Yorkshire Terrier Adult 1kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos de raza Yorkshire Terrier. A partir de los 10 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-yorkshire-adult-01_500x.png?v=1658170295", 19, 0, 1947,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal26);

			Product balanced_meal27 = new Product("Alimento Royal Canin Perro Satiety Support 15kg", "Royal Canin", "Alimento marca Royal Canin dietético completo destinado a perros que requieren una reducción del exceso de peso. Este alimento contiene un bajo tenor energético.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/royal-canin-satiety-canine-veterinary-health-nutrition-seco-1_360x.jpg?v=1658170326", 2, 0, 19424,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal27);

			Product balanced_meal28 = new Product("Alimento Royal Canin Perro Medium Weight Care 3kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos de talla mediana (de 11 a 25 kg). De más de 12 meses de edad con tendencia al sobrepeso y/o bajo nivel de actividad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/royal-canin-medium-weight-care-canine-care-nutrition-seco_360x.jpg?v=1658170357", 24, 0, 4902,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal28);

			Product balanced_meal29 = new Product("Alimento Royal Canin Perro Hepatic 1.5kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos destinado a ayudar a la función hepática en perros con insuficiencia hepática crónica y por su bajo nivel de cobre, ayuda a reducir su acumulación en el hígado.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-hepatic-01_500x.png?v=1658170408", 11, 0, 2854,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal29);

			Product balanced_meal30 = new Product("Alimento Royal Canin Perro Medium Ageing edad 10+ 15kg", "Royal Canin", "Alimento marca Royal Canin para perros maduros de talla mediana (de 11 a 25 kg). A partir de los 10 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-medium-ageing_500x.png?v=1658170364", 8, 0, 15955,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal30);

			Product balanced_meal31 = new Product("Alimento Royal Canin Perro Maxi Dermacomfort 10kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos y maduros de talla grande (de 26 a 44 kg) propensos a la irritación cutánea y al prurito. A partir de los 15 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/royal-canin-maxi-dermacomfort-canine-care-nutrition-seco_360x.jpg?v=1658170372", 3, 0, 11311,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal31);

			Product balanced_meal32 = new Product("Alimento Royal Canin Perro Urinary s/o 1.5kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos, formulado para ayudar a promover la disolución de cálculos de estruvita. Contribuye al manejo nutricional de los cálculos de oxalato de calcio y de estruvita","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-urinary-so_360x.png?v=1658170306", 15, 0, 2890,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal32);

			Product balanced_meal33 = new Product("Alimento Royal Canin Perro Renal 1.5kg", "Royal Canin", "Alimento marca Royal Canin para perros formulado para el soporte de la función renal en caso de insuficiencia renal crónica.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-renal_360x.png?v=1658170330", 29, 0, 2580,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal33);

			Product balanced_meal34 = new Product("Alimento Royal Canin Perro Hypoallergenic Small Dog 2kg", "Royal Canin", "Alimento marca Royal Canin para perros adultos de menos de 10 kg de peso formulado con fuentes seleccionadas de proteína y carbohidratos adecuadas para el manejo de reacciones adversas a ingredientes.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-perro-hypoallergenic-small-dog_360x.jpg?v=1658170403", 9, 0, 4492,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal34);

			Product balanced_meal35 = new Product("Alimento Excellent Perro Adulto Pollo y Arroz raza Mediana y Grande 3kg", "Excellent", "Alimento para Perro Adulto raza Mediana y Grande marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RenderFRENTEEXCELLENTDOGADULTSLARGEBREED_ad247ac4-94c6-44d0-a7bb-19f6e5890fd7_500x.jpg?v=1660311040", 2, 0, 2292,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal35);

			Product balanced_meal36 = new Product("Alimento Excellent Perro Adulto Pollo y Arroz raza Pequeña 1kg", "Excellent", "Alimento para Perro Adulto raza Pequeña marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RenderFRENTEEXCELLENTDOGADULTSSMALLBREED_cfd300e0-85df-4b67-aef7-8a197345e0d2_500x.jpg?v=1660311030", 21, 0, 839,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal36);

			Product balanced_meal37 = new Product("Alimento Excellent Perro Adulto Formula 20kg", "Excellent", "Alimento para Perro Adulto Formula marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RENDERFRENTEEXCELLENT_FORMULA_DOG-01_1099d4e0-2be2-4113-b3a7-ad32f6095e8c_500x.jpg?v=1660311028", 7, 0, 9702,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal37);

			Product balanced_meal38 = new Product("Alimento Excellent Perro Adulto edad 7+ Pollo y Arroz 3kg", "Excellent", "Alimento para Perro Senior marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RENDERFRENTEEXCELLENTDOGSENIOR_64fee912-53cf-448f-8c6f-2bd841e9e7a7_500x.jpg?v=1660311038", 27, 0, 2528,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal38);

			Product balanced_meal39 = new Product("Alimento Excellent Perro Cachorro Pollo y Arroz raza Mediana y Grande 3kg", "Excellent", "Alimento para Perro Cachorro raza Mediana y Grande marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RENDERFRENTEEXCELLENTDOGPUPPYLARGEBREED_6cee01b1-b55e-4b41-aa20-8b4c84e42428_500x.jpg?v=1660311022", 15, 0, 2514,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal39);

			Product balanced_meal40 = new Product("Alimento Excellent Perro Adulto Bajas Calorías Pollo y Arroz 15kg", "Excellent", "Alimento para Perro Bajas Calorías marca Excellent","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RENDERFRENTEEXCELLENTDOGRCalorie_69f942ab-881c-4483-a196-0560b8c61997_500x.jpg?v=1660311018", 17, 0, 9906,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal40);

			/* Alimento Gatos */

			Product balanced_meal57 = new Product("Alimento Balanced Gato Adulto 0.400kg", "Balanced", "Alimento para Gato Adulto marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Gato_Adulto_x2_aaf95753-ded0-43b6-8a82-9a875c7e3b9c_500x.png?v=1660597402", 24, 0, 600,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal57);

			Product balanced_meal42 = new Product("Alimento Balanced Gato Control de Peso / Castrado 2kg", "Balanced", "Alimento para Gato Control de Peso / Castrado marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Gato_Control_de_Peso_x400gr_500x.png?v=1660597398", 14, 0, 2899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal42);

			Product balanced_meal43 = new Product("Alimento Balanced Gatito Kitten 2kg", "Balanced", "Alimento para Gato Kitten / Cachorro marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Kitten_Frente_x2_9d106034-3a6c-47fe-b75f-07fe39e7e5db_500x.png?v=1660597396", 36, 0, 2899,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal43);

			Product balanced_meal44 = new Product("Alimento Balanced Gato Adulto Pollo y Arroz 3kg", "Balanced", "Alimento de Pollo y Arroz para Gato Adulto marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-balanced-gato-adulto-pollo-y-arroz-01_43ac3e24-3739-4a2f-8b09-bf6fd9ba36f2_500x.png?v=1660597372", 9, 0, 3499,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal44);

			Product balanced_meal45 = new Product("Alimento Balanced Gato Senior 0.400kg", "Balanced", "Alimento para Gato Senior marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Gato_senior_x2_f05f9d85-cfad-4b8e-bee6-cbddbed760dc_500x.png?v=1660597393", 35, 0, 699,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal45);

			Product balanced_meal46 = new Product("Alimento Balanced Gato Control ph 0.400kg", "Balanced", "Alimento para Gato Control PH marca Balanced","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/Gato_Ph_x2_ac6733eb-407b-4cb7-aa71-73a2ebb7948d_500x.png?v=1660597400", 15, 0, 699,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal46);

			Product balanced_meal47 = new Product("Alimento Royal Canin Gato Urinary s/o High Dilution 0.400kg", "Royal Canin", "Formulado para promover una alta dilución urinaria, ayudando a la disolución de cálculos de estruvita. Contribuye al manejo nutricional de los cálculos de oxalato de calcio y de estruvita.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-urinary-high-dilution_360x.png?v=1658170301", 30, 0, 1316,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal47);

			Product balanced_meal48 = new Product("Alimento Royal Canin Gato Fit 1.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos adultos con peso ideal, actividad física moderada y que poseen acceso al exterior. De 1 a 7 años de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-fit-01_500x.png?v=1658170444", 14, 0, 3571,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal48);

			Product balanced_meal49 = new Product("Alimento Royal Canin Gatito Kitten 7.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos, especial para gatitos en su segunda etapa de crecimiento. Desde los 4 hasta los 12 meses de edad.","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-kitten_360x.jpg?v=1658170395", 3, 0, 14294,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal49);

			Product balanced_meal50 = new Product("Alimento Royal Canin Gato Indoor 1.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos adultos que viven en el interior del hogar - A partir del año de edad.", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-indoor_360x.png?v=1658170401", 18, 0, 3570,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal50);

			Product balanced_meal51 = new Product("Alimento Royal Canin Gatos Castrados Young Male 1.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos machos desde la castración hasta los 7 años de edad.", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-castrado.young-male-01_500x.png?v=1658170425", 8, 0, 3360,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal51);

			Product balanced_meal52 = new Product("Alimento Royal Canin Gato Urinary Care 7.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos adultos recomendado para ayudar a mantener la salud del tracto urinario.", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/alimento-royal-canin-gato-urinary-care_360x.png?v=1658170303", 3, 0, 14800,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal52);

			Product balanced_meal53 = new Product("Alimento Royal Canin Gatos Castrados Weight Control 1.5kg", "Royal Canin", "Alimento marca Royal Canin para gatos adultos castrados que ayuda a mantenerlos en estado óptimo de salud.", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/royal-canin-gatos-castrados-weight-control_6bd2f3a4-0f9b-463e-9a1c-cbf926bc1973_500x.jpg?v=1658170431", 19, 0, 3955,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal53);

			Product balanced_meal54 = new Product("Alimento Excellent Gato Adulto Pollo y Arroz 3kg", "Excellent", "Alimento para Gato Adulto marca Excellent", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RenderFRENTEEXCELLENTCATADULT_9f9ee33a-f204-4a31-b5f5-cb51b21e1158_500x.jpg?v=1660311035", 1, 0, 3840,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal54);

			Product balanced_meal55 = new Product("Alimento Excellent Gato Urinary 1kg", "Excellent", "Alimento para Gato con dificultades Urinarias marca Excellent", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RenderFRENTEEXCELLENTUrinary_79a4ef7f-c57e-494e-8941-f76b3cfa29f7_500x.jpg?v=1660311017", 16, 0, 1633,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal55);

			Product balanced_meal56 = new Product("Alimento Excellent Gatito Kitten Pollo y Arroz 7.5kg", "Excellent", "Alimento para Gato Kitten / Cachorro marca Excellent", "https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RenderFRENTEEXCELLENTKITTEN_ffab49ad-55cc-4388-8ad2-e4a3bccb91a5_500x.jpg?v=1660311025", 9, 0, 8752,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal56);

			/* Alimento conejos */

			Product balanced_meal61 = new Product("Alimento Zootec Nutribits Conejo - 600 gr", "Zootec", "El Alimento Zootec Nutribits Conejo ofrece una dieta completa formulada para cumplir los requerimientos nutricionales de tus conejos durante todas las etapas de la vida.", "https://puppis.vteximg.com.br/arquivos/ids/185605-1000-1000/142102.jpg?v=637823483010030000", 13, 0, 7359,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal61);

			Product balanced_meal58 = new Product("Alimento Balanceado Conejos Especial Balanceado 3 Kg", "Land’s", "Excelente balanceado a base de alfalfa y cereales con crunchies de frutas y verduras. Alimento a base de materias primas de primera calidad que constituye una dieta balanceada a la medida de su mascota.", "https://http2.mlstatic.com/D_NQ_NP_915956-MLA49111261432_022022-O.webp", 32, 0, 1400,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal58);

			Product balanced_meal59 = new Product("Alimento Nelsoni Ranch Para Conejos - 750Gr", "Nelsoni Ranch", "Alimento Balanceado para conejos.", "https://http2.mlstatic.com/D_NQ_NP_915956-MLA49111261432_022022-O.webp", 39, 0, 550,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal59);

			Product balanced_meal60 = new Product("Conejo Land’s rabbit 1kg", "Land’s", "Se puede complementar con comida fresca, ya que le encantará la variedad de sabores. Estos roedores pasan muchas horas del día masticando y comiendo", "https://guauyeah.com.ar/wp-content/uploads/2021/11/conejo-lands1-ae56939f55df6208cd15936208916819-1024-1024.jpg", 21, 0, 298,ProductCategory.BALANCED_MEAL);
			productService.saveProduct(balanced_meal60);



			/* Accesorios */
			Product toy = new Product("Rascador Ami Simón Estampado. ","Ami","Rascador para Gatos marca Ami modelo Simón Estampado Medidas: 48cm largo x 20cm ancho x 8cm alto","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-ami-simon-estampado_500x.png?v=1659539378",26,0,1933,ProductCategory.TOY);
			productService.saveProduct(toy);

			Product toy1 = new Product("Rascador Ami Jagger Estampado","Ami","Rascador para Gatos marca Ami modelo Jagger Estampado Medidas: 48cm largo x 20cm ancho x 8cm alto","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-ami-jagger-estampado_a8aa6588-f5e9-4b73-910d-06d9cb676534_500x.png?v=1659539509",14,0,1933,ProductCategory.TOY);
			productService.saveProduct(toy1);

			Product toy2 = new Product("Rascador Ami Tiffany Estampado","Ami","Rascador para Gatos marca Ami modelo Tiffany Estampado","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-ami-tinfanny-estampado_500x.png?v=1659539211",4,0,1933,ProductCategory.TOY);
			productService.saveProduct(toy2);

			Product toy3 = new Product("Rasqueta Oval para Perros y Gatos","Pets Plast","Rasqueta Oval para cepillar y bañar a tu mascota","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/RASQUETAOVAL_500x.jpg?v=1632770023",16,0,755,ProductCategory.TOY);
			productService.saveProduct(toy3);

			Product toy4 = new Product("Cepillo Peine Manopla de Goma","Pets Plast","Manopla de Goma para cepillar marca Pets Plast","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/PeineManopladeGomaPetsPlast_500x.jpg?v=1632770257",12,0,590,ProductCategory.TOY);
			productService.saveProduct(toy4);

			Product toy5 = new Product("Pañales Pañopet Mini x 3 unidades","Pets Plast","Pañales para Perros Pequeños Tamaños: Chico (hasta 1,5kg) Mediano (hasta 3kg) Grande (hasta 4,5kg) Extra Grande (hasta 6kg)","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/PanalPanopet-01_500x.jpg?v=1632770005",14,0,650,ProductCategory.TOY);
			productService.saveProduct(toy5);

			Product toy6 = new Product("Comedero Perro Metálico Liso","Pets Plast","Comedero metálico acero inoxidable anti deslisable importado","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/COMEDEROMETALLISO34CMIMPORTADO_67c84924-b324-4fdf-98c3-989ae728a4f0_500x.jpg?v=1655122942",3,0,2068,ProductCategory.TOY);
			productService.saveProduct(toy6);

			Product toy7 = new Product("Rascador Ami Paris","Ami","Rascador para Gatos marca Ami modelo París","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-ami-paris-2_500x.png?v=1659539066",2,0,2698,ProductCategory.TOY);
			productService.saveProduct(toy7);

			Product toy8 = new Product("Rascador Ami Land","Ami","Rascador para Gatos marca Ami modelo Land","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-land-ami-gatos_500x.jpg?v=1658759752",4,0,6233,ProductCategory.TOY);
			productService.saveProduct(toy8);

			Product toy9 = new Product("Rascador Ami Simón Madera","Ami","Rascador para Gatos marca Ami modelo Simón Madera Medidas: 48cm largo x 20cm ancho x 8cm alto","https://cdn.shopify.com/s/files/1/0549/6848/4082/products/rascador-ami-simon-madera_500x.png?v=1659539399",10,0,2899,ProductCategory.TOY);
			productService.saveProduct(toy9);

			Product toy10 = new Product("Rollo Bolsitas Cancat - 3 Unid","Cancat","Rollo Bolsitas Cancat - 3 Unid","https://puppis.vteximg.com.br/arquivos/ids/181222-1000-1000/cancat.jpg?v=637630105658030000",3,0,710,ProductCategory.TOY);
			productService.saveProduct(toy10);

			Product toy11 = new Product("Set Puppis Dental Cachorro Azul","Puppis","Set Puppis Dental Cachorro Azul","https://puppis.vteximg.com.br/arquivos/ids/185806-1000-1000/269197-2.jpg?v=637832328390870000",3,0,1460,ProductCategory.TOY);
			productService.saveProduct(toy11);

			Product toy12 = new Product("Pelota Pesada Irregular - ø7 Cm","Puppis","Pelota Pesada Irregular - ø7 Cm","https://puppis.vteximg.com.br/arquivos/ids/165714-1000-1000/221058.png?v=636772051523330000",20,0,1192,ProductCategory.TOY);
			productService.saveProduct(toy12);

			Product toy13 = new Product("Colchoneta Brakko Dormilon Liso - 59x70 cm","Brakko","Colchoneta Brakko Dormilon Liso - 59x70 cm","https://puppis.vteximg.com.br/arquivos/ids/189103-1000-1000/200672.jpg?v=637974593324630000",3,0,2290,ProductCategory.TOY);
			productService.saveProduct(toy13);

			Product toy14 = new Product("Correa Retractil Flexi New Comfort Roja - XS","Puppis","Correa Retractil Flexi New Comfort Roja - XS","https://puppis.vteximg.com.br/arquivos/ids/185791-1000-1000/256193.jpg?v=637832328324870000",6,0,3840,ProductCategory.TOY);
			productService.saveProduct(toy14);

			Product toy15 = new Product("Bolsas Erres Biodegradables - 20 Unid","Pets Plast","Bolsas Erres Biodegradables - 20 Unid","https://puppis.vteximg.com.br/arquivos/ids/188542-1000-1000/233156.jpg?v=637946593392030000",9,0,540,ProductCategory.TOY);
			productService.saveProduct(toy15);

			Product toy16 = new Product("Collar Marvel para Perro - XS","Puppis","Collar Marvel para Perro - XS","https://puppis.vteximg.com.br/arquivos/ids/180022-1000-1000/233103.jpg?v=637571246765870000",4,0,1670,ProductCategory.TOY);
			productService.saveProduct(toy16);

			Product toy17 = new Product("Juguete Antiestres Cancat Hueso Saborizado","Pets Plast","Juguete Antiestres Cancat Hueso Saborizado","https://puppis.vteximg.com.br/arquivos/ids/172095-1000-1000/221184.jpg?v=637324241706930000",4,0,3289,ProductCategory.TOY);
			productService.saveProduct(toy17);

			Product toy18 = new Product("Juguete Rast Pez TPR","Pets Plast","Juguete Rast Pez TPR","https://puppis.vteximg.com.br/arquivos/ids/173369-1000-1000/229238.jpg?v=637395949229100000",9,0,360,ProductCategory.TOY);
			productService.saveProduct(toy18);

			Product toy19 = new Product("Cucha Térmica Durapets Roja","Durapets","Cucha Térmica Durapets Roja - 4","https://puppis.vteximg.com.br/arquivos/ids/183266-1000-1000/252213.jpg?v=637698190685970000",1,0,18801,ProductCategory.TOY);
			productService.saveProduct(toy19);

			Product toy20 = new Product("Correa Retractil Flexi New Comfort Celeste - M","Puppis","Correa Retractil Flexi New Comfort Celeste","https://puppis.vteximg.com.br/arquivos/ids/185795-1000-1000/256199.jpg?v=637832328342500000",7,0,5665,ProductCategory.TOY);
			productService.saveProduct(toy20);

			Product toy21 = new Product("Carrito Pawise Pet Carrier Nylon","Pet Carrier","Carrito Pawise Pet Carrier Nylon - 50x27x29 cm","https://puppis.vteximg.com.br/arquivos/ids/186625-1000-1000/237644.jpg?v=637878708151330000",9,0,5643,ProductCategory.TOY);
			productService.saveProduct(toy21);

			Product toy22 = new Product("Pelota Kong Squeezz Action Roja","Kong Squeezz","Pelota Kong Squeezz Action Roja","https://puppis.vteximg.com.br/arquivos/ids/182665-1000-1000/224398.jpg?v=637673987182970000",8,0,3700,ProductCategory.TOY);
			productService.saveProduct(toy22);

			Product toy23 = new Product("Dispenser M-Pets de Agua Lena Antideslizante Blanco","M-Pets","Dispenser M-Pets de Agua Lena Antideslizante Blanco - 3,5 Lts","https://puppis.vteximg.com.br/arquivos/ids/187859-1000-1000/237709.jpg?v=637931452340470000",9,0,4490,ProductCategory.TOY);
			productService.saveProduct(toy23);

			Product toy24 = new Product("Arnes Puppis Basic Nylon Rojo","Puppis","Arnes Puppis Basic Nylon Rojo","https://puppis.vteximg.com.br/arquivos/ids/184348-1000-1000/269145.jpg?v=637734567115500000",3,0,1950,ProductCategory.TOY);
			productService.saveProduct(toy24);

			Product toy25 = new Product("Arnes Puppis Basic Nylon Negro","Puppis","Arnes Puppis Basic Nylon Negro","https://puppis.vteximg.com.br/arquivos/ids/184346-1000-1000/269143.jpg?v=637734567108600000",6,0,1950,ProductCategory.TOY);
			productService.saveProduct(toy25);

			Product toy26 = new Product("Transportadora Cancat Skudo 3","Pets Plast","Transportadora Cancat Skudo 3 - 60x40x39","https://puppis.vteximg.com.br/arquivos/ids/173868-1000-1000/221204.jpg?v=637414752932630000",2,0,30990,ProductCategory.TOY);
			productService.saveProduct(toy26);

			Product toy27 = new Product("Bebedero Dogit Fountain","Dogit","Bebedero Dogit Fountain - 6L","https://puppis.vteximg.com.br/arquivos/ids/189176-1000-1000/228322.jpg?v=637979871128900000",6,0,7597,ProductCategory.TOY);
			productService.saveProduct(toy27);

			Product toy28 = new Product("Arnes Cocooning Joseph Gris Lima","Cocooning","Arnes Cocooning Joseph Gris Lima","https://puppis.vteximg.com.br/arquivos/ids/173395-1000-1000/248059.jpg?v=637395949315730000",9,0,4860,ProductCategory.TOY);
			productService.saveProduct(toy28);

			Product toy29 = new Product("Pelota Con Soga Cancat Wavy Rubber","Cancat","Pelota Con Soga Cancat Wavy Rubber - 6x30 Cm","https://puppis.vteximg.com.br/arquivos/ids/170567-1000-1000/221147.jpg?v=637171179673630000",15,0,1270,ProductCategory.TOY);
			productService.saveProduct(toy29);

			Product toy30 = new Product("Pelota Con Soga Fitness K9","Puppis","Pelota Con Soga Fitness K9 - 23 Cm","https://puppis.vteximg.com.br/arquivos/ids/175657-1000-1000/206008.jpg?v=637499631074570000",7,0,1143,ProductCategory.TOY);
			productService.saveProduct(toy30);

			Product toy31 = new Product("Cucha Bubble Traslucida","Bubble","Cucha Bubble Traslucida - Mediana","https://puppis.vteximg.com.br/arquivos/ids/160440-1000-1000/263042.jpg?v=636392518993630000",17,0,10250,ProductCategory.TOY);
			productService.saveProduct(toy31);

			Product toy32 = new Product("Peluche M-Pets Charly Perro Marrón","M-Pets","Peluche M-Pets Charly Perro Marrón - 1 Un","https://puppis.vteximg.com.br/arquivos/ids/188018-1000-1000/237797.jpg?v=637934995458470000",6,0,2250,ProductCategory.TOY);
			productService.saveProduct(toy32);

			Product toy33 = new Product("Moises Puppis Nido Corderito","Puppis","Moises Puppis Nido Corderito - 55x17 cm","https://puppis.vteximg.com.br/arquivos/ids/186419-1000-1000/269231.jpg?v=637865778768370000",3,0,5707,ProductCategory.TOY);
			productService.saveProduct(toy33);

			Product toy34 = new Product("Juguete Pawise Botella Unicornio","Pawise","Juguete Pawise Botella Unicornio","https://puppis.vteximg.com.br/arquivos/ids/183180-1000-1000/237667.jpg?v=637698186482430000",12,0,1140,ProductCategory.TOY);
			productService.saveProduct(toy34);

			Product toy35 = new Product("Juguete Pawise Botella Alpaca","Pawise","Juguete Pawise Botella Alpaca","https://puppis.vteximg.com.br/arquivos/ids/183179-1000-1000/237666.jpg?v=637698186477730000",15,0,1140,ProductCategory.TOY);
			productService.saveProduct(toy35);

			Product toy36 = new Product("Cable de Amarre Dogit Rojo","Dogit","Cable de Amarre Dogit Rojo - 6 mts","https://puppis.vteximg.com.br/arquivos/ids/180114-1000-1000/231088.jpg?v=637579992217000000",12,0,2290,ProductCategory.TOY);
			productService.saveProduct(toy36);

			Product toy37 = new Product("Juguete Gigwi Johnny Stick","Gigwi","Juguete Gigwi Johnny Stick","https://puppis.vteximg.com.br/arquivos/ids/177291-1000-1000/255333.jpg?v=637567929754230000",8,0,3445,ProductCategory.TOY);
			productService.saveProduct(toy37);

			Product toy38 = new Product("Pelota Gigwi Squeaker Solid Roja","Gigwi","Pelota Gigwi Squeaker Solid Roja","https://puppis.vteximg.com.br/arquivos/ids/177342-1000-1000/255316.jpg?v=637567929913270000",18,0,2015,ProductCategory.TOY);
			productService.saveProduct(toy38);

			Product toy39 = new Product("Moises WePets Panama Gris","WePets","Moises WePets Panama Gris","https://puppis.vteximg.com.br/arquivos/ids/176783-1000-1000/235042.jpg?v=637556935683870000",4,0,9820,ProductCategory.TOY);
			productService.saveProduct(toy39);

			Product toy40 = new Product("Moises Dogit Cuddle Redondo Gris","Dogit","El Moises Dogit Cuddle Redondo, le ofrece a tu mascota un lugar de descanso cómodo y acogedor. Está fabricado con materiales de alta calidad, asegurando una textura acolchada y suave.","https://puppis.vteximg.com.br/arquivos/ids/176518-1000-1000/245165.jpg?v=637546240199300000",6,0,8860,ProductCategory.TOY);
			productService.saveProduct(toy40);

			Product toy41 = new Product("Chapita My Family Classic Pawprint Roja","Dogit","Chapita My Family Classic Pawprint Roja","https://puppis.vteximg.com.br/arquivos/ids/176018-1000-1000/252175.png?v=637521108575830000",5,0,3700,ProductCategory.TOY);
			productService.saveProduct(toy41);

			Product toy42 = new Product("Juguete Marvel Capitan America Mordedor","Puppis","Juguete Marvel Capitan America Mordedor","https://puppis.vteximg.com.br/arquivos/ids/178859-1000-1000/233136.jpg?v=637569582597730000",6,0,4000,ProductCategory.TOY);
			productService.saveProduct(toy42);

			Product toy43 = new Product("Juguete Cancat Hueso Dental","CanCat","Juguete Cancat Hueso Dental - 4x11,5 Cm","https://puppis.vteximg.com.br/arquivos/ids/170569-1000-1000/221142.jpg?v=637171179682830000",1,0,3490,ProductCategory.TOY);
			productService.saveProduct(toy43);

			Product toy44 = new Product("Pelota Gigwi Transparente Squeaker","Gigwi","Pelota Gigwi Transparente Squeaker","https://puppis.vteximg.com.br/arquivos/ids/162882-1000-1000/228718.jpg?v=636559313780130000",13,0,1475,ProductCategory.TOY);
			productService.saveProduct(toy44);

			Product toy45 = new Product("Bolso Transportador Poroto Fashion","Gigwi","Bolso Transportador Poroto Fashion - Grande","https://puppis.vteximg.com.br/arquivos/ids/186095-1000-1000/256030-01.jpg?v=637838194355830000",7,0,8811,ProductCategory.TOY);
			productService.saveProduct(toy45);

			Product toy46 = new Product("Escalera Plegable M-Pets Gris - Único","Gigwi","Escalera Plegable M-Pets Gris - Único","https://puppis.vteximg.com.br/arquivos/ids/188040-1000-1000/237810.png?v=637934995539270000",2,0,16000,ProductCategory.TOY);
			productService.saveProduct(toy46);

			Product toy47 = new Product("Peluche M-Pets Franki Cocodrilo Verde - 1 Un","M-Pets","El Peluche M-Pets Franki Cocodrilo Verde, el Cocodrilo Franki es una buena elección para que tu mascota disfrute jugando. El material es Polyester.","https://puppis.vteximg.com.br/arquivos/ids/188019-1000-1000/237798.jpg?v=637934995462530000",6,0,2220,ProductCategory.TOY);
			productService.saveProduct(toy47);

			Product toy48 = new Product("Soga CanCat Flotadora Doble - 30 Cm","CanCat","La Soga CanCat Flotadora, con nudos para tirar y forcejear con su perro. Los perros adoran los juegos en los que ponen a prueba sus fuerzas. La soga es robusta y ligera.","https://puppis.vteximg.com.br/arquivos/ids/187683-1000-1000/221386.jpg?v=637919356534370000",8,0,4080,ProductCategory.TOY);
			productService.saveProduct(toy48);

			Product toy49 = new Product("Comedero Doble Acero Inoxidable Puppis 0.85 L","Pets Plast","El Comedero de acero inoxidable, cuenta con una base de goma antideslizante para una mejor alimentación perruna.","https://puppis.vteximg.com.br/arquivos/ids/189252-1000-1000/269359.jpg?v=637979987016330000",13,0,4185,ProductCategory.TOY);
			productService.saveProduct(toy49);

			Product toy50 = new Product("Colchón Canine Coture Dona 70 Cm","Coture","El Colchon ConinCoture Dona 70 Cm Rosa, es el producto ideal para el descanso de tu mascota. Su forma es circular, está fabricado con Tela Trucker, Funda Dona con fuelle y diseño Pop Art.","https://puppis.vteximg.com.br/arquivos/ids/187696-1000-1000/262143.jpg?v=637919356587270000",10,0,12390,ProductCategory.TOY);
			productService.saveProduct(toy50);
		};
	}
}

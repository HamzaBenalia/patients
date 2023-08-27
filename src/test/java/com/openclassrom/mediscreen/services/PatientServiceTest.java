package com.openclassrom.mediscreen.services;
import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.repositories.PatientsRepository;
import com.openclassrom.mediscreen.services.Impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientsRepository patientsRepository;

    @Captor
    private ArgumentCaptor<Patient> patientArgumentCaptor;


    @Test
    public void testSavePatient() {
        Patient patient = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");
        patientServiceImpl.savePatient(patient);

        verify(patientsRepository).save(patientArgumentCaptor.capture());
        Patient patientCaptor = patientArgumentCaptor.getValue();
        assertThat(patientCaptor).isEqualTo(patient);

    }

    @Test
    public void testListAll(){
        List<Patient> patientList = new ArrayList<>();
        Patient patientHamza = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");
        Patient patientSara = new Patient(2, "Benalia", "Sara", "16/05/1997", "Femme", "Touluse", "0722335544");

        patientList.add(patientHamza);
        patientList.add(patientSara);

        Mockito.when(patientsRepository.findAll()).thenReturn(patientList);


        List<Patient> actualPatients = patientServiceImpl.listAll();
        assertThat(actualPatients).isEqualTo(patientList);
    }

    @Test
    public void testGetPatientById() throws PatientNotFoundException {

        Patient patient = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");

        Mockito.when(patientsRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        Patient actualPatient = patientServiceImpl.getPatient(1);

        assertThat(actualPatient).isEqualTo(patient);

    }

    @Test
    public void testGetPatientByIdThrowException() throws PatientNotFoundException {

        Patient patient = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");

        Mockito.when(patientsRepository.findById(patient.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(PatientNotFoundException.class, () -> {
            patientServiceImpl.getPatient(patient.getId());
        });

        // Vous pouvez même vérifier le message de l'exception si vous le souhaitez
        String expectedMessage = "Patient non trouvé avec l'ID : " + patient.getId();

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testGetPatientByNom() {
        List<Patient> patientList = new ArrayList<>();

        Patient patientHamza = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");
        Patient patientSara = new Patient(2, "johan", "Sara", "16/05/1997", "Femme", "Touluse", "0722335544");

        patientList.add(patientHamza);
        patientList.add(patientSara);
        Mockito.when(patientsRepository.findByNom(patientHamza.getNom())).thenReturn(Arrays.asList(patientHamza));

        List<Patient> actualPatients = patientServiceImpl.findByNom("Benalia");

        assertThat(actualPatients.get(0)).isEqualTo(patientHamza);
    }

    @Test
    public void testUpdatePatient() throws PatientNotFoundException {
        // Créez un patient existant et un nouveau patient avec des détails mis à jour
        Patient existingPatient = new Patient(1, "Benalia", "Hamza", "16/05/1995", "Homme", "Touluse", "0766764619");
        Patient newPatientDetails = new Patient(1, "Benalia", "Ahmed", "16/05/1996", "Homme", "Paris", "0766764610");

        Mockito.when(patientsRepository.findById(existingPatient.getId())).thenReturn(Optional.of(existingPatient));
        // Configurez également le comportement de save pour renvoyer le nouvel objet patient
        Mockito.when(patientsRepository.save(any(Patient.class))).thenAnswer(i -> i.getArguments()[0]);

        Patient updatedPatient = patientServiceImpl.updatePatient(existingPatient.getId(), newPatientDetails);

        assertThat(updatedPatient.getNom()).isEqualTo(newPatientDetails.getNom());
        assertThat(updatedPatient.getPrenom()).isEqualTo(newPatientDetails.getPrenom());
        assertThat(updatedPatient.getGenre()).isEqualTo(newPatientDetails.getGenre());
        assertThat(updatedPatient.getAdressePostale()).isEqualTo(newPatientDetails.getAdressePostale());
        assertThat(updatedPatient.getNumeroDeTelephone()).isEqualTo(newPatientDetails.getNumeroDeTelephone());

        verify(patientsRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void deletePatientTest() {
        Integer id = 1;

        // Simuler le comportement de patientsRepository.deleteById(id)
        doNothing().when(patientsRepository).deleteById(id);

        String result = patientServiceImpl.deletePatient(id);

        // Vérifier si patientsRepository.deleteById(id) a été appelé
        verify(patientsRepository, times(1)).deleteById(id);

        // Vérifier le retour de la fonction
        assertEquals("patient supprimé", result);
    }
}



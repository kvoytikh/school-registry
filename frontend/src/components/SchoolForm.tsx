import React, { useState } from 'react';
import {SchoolType } from '../shared/enums/SchoolType.ts';
import styles from './SchoolForm.module.css'
import type {SchoolFormData} from "../shared/types/schoolFormData.ts";

interface SchoolFormProps {
    onCreate: (schoolData: SchoolFormData) => Promise<void>;
    onCancel: () => void;
    schoolTypes: SchoolType[];
    isLoading?: boolean;
}

const SchoolForm: React.FC<SchoolFormProps> = ({ onCreate, onCancel, schoolTypes, isLoading }) => {
    const [formData, setFormData] = useState<SchoolFormData>({
        name: '',
        edrpou: '',
        region: '',
        schoolType: schoolTypes[0] || SchoolType.ZZSO,
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!formData.name || !formData.edrpou || !formData.region || !formData.schoolType) {
            alert('Будь ласка, заповніть всі обов\'язкові поля.');
            return;
        }
        await onCreate(formData);
    };

    return (
        <form onSubmit={handleSubmit} className={styles.schoolForm}>
            <h2 className={styles.formTitle}>Створити нову школу</h2>
            <div className={styles.formGroup}>
                <label htmlFor="name" className={styles.formLabel}>Назва:</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                    disabled={isLoading}
                    className={styles.formInput}
                />
            </div>
            <div className={styles.formGroup}>
                <label htmlFor="edrpou" className={styles.formLabel}>ЄДРПОУ:</label>
                <input
                    type="text"
                    id="edrpou"
                    name="edrpou"
                    value={formData.edrpou}
                    onChange={handleChange}
                    required
                    disabled={isLoading}
                    className={styles.formInput}
                />
            </div>
            <div className={styles.formGroup}>
                <label htmlFor="region" className={styles.formLabel}>Область:</label>
                <input
                    type="text"
                    id="region"
                    name="region"
                    value={formData.region}
                    onChange={handleChange}
                    required
                    disabled={isLoading}
                    className={styles.formInput}
                />
            </div>
            <div className={styles.formGroup}>
                <label htmlFor="schoolType" className={styles.formLabel}>Тип:</label>
                <select
                    id="schoolType"
                    name="schoolType"
                    value={formData.schoolType}
                    onChange={handleChange}
                    required
                    disabled={isLoading}
                    className={styles.formSelect}
                >
                    {schoolTypes.map(type => (
                        <option key={type} value={type}>{type}</option>
                    ))}
                </select>
            </div>
            <div className={styles.formButtons}>
                <button type="submit" disabled={isLoading} className={styles.submitButton}>
                    {isLoading ? 'Створення...' : 'Створити'}
                </button>
                <button type="button" onClick={onCancel} disabled={isLoading} className={styles.cancelButton}>
                    Скасувати
                </button>
            </div>
        </form>
    );
};

export default SchoolForm;